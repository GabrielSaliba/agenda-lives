package com.agendalive.controller;

import com.agendalive.domain.dto.LiveDTO;
import com.agendalive.domain.entity.Live;
import com.agendalive.service.LiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;

    @GetMapping
    public Live saveLive(@RequestBody @Valid LiveDTO live) {
        return liveService.saveLive(live);
    }
}
