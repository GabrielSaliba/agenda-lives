package com.agendalive.mock;

import com.agendalive.domain.entity.Live;
import com.agendalive.domain.enums.LiveStatus;
import com.agendalive.repository.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LiveMock {

    @Autowired
    LiveRepository liveRepository;

    public Live mockLive() {
        Live live = new Live();
        live.setName("Testing Name");
        live.setChannel("Testing Channel");
        live.setLiveUrl("https://testing/url.com");
        live.setStatus(LiveStatus.COMING);
        live.setStartDate(LocalDateTime.now().plusDays(1));
        live.setEndDate(LocalDateTime.now().plusDays(5));
        return live;
    }

    public Live persistLive(Live live) {
        return liveRepository.save(live);
    }

    public Live persistLive() {
        return liveRepository.save(mockLive());
    }

    public Live getLive(Long id) {
        return liveRepository.findById(id).orElse(null);
    }
}
