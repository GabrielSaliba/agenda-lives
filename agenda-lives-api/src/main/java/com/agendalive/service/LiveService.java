package com.agendalive.service;

import com.agendalive.domain.entity.Live;
import com.agendalive.repository.LiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final LiveRepository liveRepository;

    public Live saveLive(Live live) {
        live.setId(null);
        return liveRepository.save(live);
    }
}
