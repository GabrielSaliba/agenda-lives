package com.agendalive.controller;

import com.agendalive.domain.entity.Live;
import com.agendalive.domain.enums.LiveStatus;
import com.agendalive.mock.LiveMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class LiveControllerTest {

    private static final String BASE_URL = "/live";

    @Autowired
    private LiveMock liveMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findLives() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getLiveById() throws Exception {
        Live live = liveMock.persistLive();
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + live.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(live.getId()));
    }

    @Test
    void saveLive() throws Exception {
        Live live = liveMock.mockLive();
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(live)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateLive() throws Exception {
        Live live = liveMock.persistLive();
        live.setName("Updated Name");
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(live)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteLive() throws Exception {
        Live live = liveMock.persistLive();
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + live.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateStatusComing() throws Exception {
        Live live = liveMock.mockLive();
        live.setStatus(null);
        live = liveMock.persistLive(live);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/update-status"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Live liveUpdated = liveMock.getLive(live.getId());
        Assertions.assertEquals(LiveStatus.COMING, liveUpdated.getStatus());
    }

    @Test
    void updateStatusFinished() throws Exception {
        Live live = liveMock.mockLive();
        live.setStatus(null);
        live.setStartDate(LocalDateTime.now().minusDays(5));
        live.setEndDate(LocalDateTime.now().minusDays(2));
        live = liveMock.persistLive(live);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/update-status"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Live liveUpdated = liveMock.getLive(live.getId());
        Assertions.assertEquals(LiveStatus.FINISHED, liveUpdated.getStatus());
    }

    @Test
    void updateStatusOngoing() throws Exception {
        Live live = liveMock.mockLive();
        live.setStatus(null);
        live.setStartDate(LocalDateTime.now().minusDays(1));
        live.setEndDate(LocalDateTime.now().plusDays(5));
        live = liveMock.persistLive(live);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/update-status"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Live liveUpdated = liveMock.getLive(live.getId());
        Assertions.assertEquals(LiveStatus.ONGOING, liveUpdated.getStatus());
    }

    @Test
    void saveLiveDateException() throws Exception {
        Live live = liveMock.mockLive();
        live.setStartDate(LocalDateTime.now());
        live.setEndDate(LocalDateTime.now().minusDays(1));
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(live)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deleteLiveNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + "0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void updateLiveNotFoundException() throws Exception {
        Live live = liveMock.persistLive();
        live.setId(0L);
        live.setName("Updated Name");
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(live)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
