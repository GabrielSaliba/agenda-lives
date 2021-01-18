package com.agendalive.service;

import com.agendalive.controller.ResponseDTO;
import com.agendalive.domain.dto.LiveDTO;
import com.agendalive.domain.entity.Live;
import com.agendalive.domain.mapper.LiveMapper;
import com.agendalive.repository.LiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final LiveRepository liveRepository;
    private final LiveMapper liveMapper;

    public ResponseDTO saveLive(LiveDTO liveDTO) {
        liveDTO.setId(null);
        Live live = liveRepository.save(liveMapper.toEntity(liveDTO));
        return new ResponseDTO(HttpStatus.CREATED, "Successfully created", live.getId().toString());
    }

    public List<LiveDTO> getLives() {
        return liveMapper.toDtoList(liveRepository.findAll());
    }
}
