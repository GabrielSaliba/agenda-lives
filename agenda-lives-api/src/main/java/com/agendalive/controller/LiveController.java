package com.agendalive.controller;

import com.agendalive.domain.dto.LiveDTO;
import com.agendalive.domain.dto.ResponseDTO;
import com.agendalive.service.LiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseDTO saveLive(@RequestBody @Valid LiveDTO live) {
        return liveService.saveLive(live);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<LiveDTO> getLives() {
        return liveService.getLives();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public LiveDTO getLiveById(@PathVariable Long id) {
        return liveService.getLiveById(id);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDTO updateLive(@RequestBody @Valid LiveDTO liveDTO) {
        return liveService.updateLive(liveDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDTO deleteLiveById(@PathVariable Long id) {
        return liveService.deleteLiveById(id);
    }

    @GetMapping("/update-status")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDTO updateLiveStatus() {
        return liveService.updateLiveStatus();
    }
}
