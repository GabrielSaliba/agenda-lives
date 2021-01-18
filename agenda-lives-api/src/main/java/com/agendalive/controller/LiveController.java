package com.agendalive.controller;

import com.agendalive.domain.dto.LiveDTO;
import com.agendalive.service.LiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;

    @PostMapping
    public ResponseDTO saveLive(@RequestBody @Valid LiveDTO live) {
        return liveService.saveLive(live);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<LiveDTO> getLives() {
        return liveService.getLives();
    }
}
