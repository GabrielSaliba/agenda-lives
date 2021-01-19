package com.agendalive.service;

import com.agendalive.domain.dto.LiveDTO;
import com.agendalive.domain.dto.ResponseDTO;
import com.agendalive.domain.entity.Live;
import com.agendalive.domain.enums.LiveStatus;
import com.agendalive.domain.mapper.LiveMapper;
import com.agendalive.repository.LiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final LiveRepository liveRepository;
    private final LiveMapper liveMapper;

    public ResponseDTO saveLive(LiveDTO liveDTO) {
        liveDTO.setId(null);
        if (liveDTO.getStartDate().isAfter(liveDTO.getEndDate())) {
            throw incorrectDate();
        }
        Live live = liveRepository.save(liveMapper.toEntity(liveDTO));
        return new ResponseDTO(HttpStatus.CREATED, "Successfully created", live.getId().toString());
    }

    public List<LiveDTO> getLives() {
        return liveMapper.toDtoList(liveRepository.findAll());
    }

    public LiveDTO getLiveById(Long id) {
        Live live = liveRepository.findById(id).orElseThrow(() -> liveNotFound(id));
        return liveMapper.toDto(live);
    }

    public ResponseDTO deleteLiveById(Long id) {
        if (Boolean.FALSE.equals(liveRepository.existsById(id))) {
            throw liveNotFound(id);
        }
        liveRepository.deleteById(id);
        return new ResponseDTO(HttpStatus.OK, "Live deleted", id.toString());
    }


    public ResponseDTO updateLiveStatus() {
        List<Live> livesList = liveRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        livesList.forEach(live -> {
            if (live.getStartDate().isAfter(now)) {
                live.setStatus(LiveStatus.COMING);
            } else if (live.getStartDate().isBefore(now) && live.getEndDate().isAfter(now)) {
                live.setStatus(LiveStatus.ONGOING);
            }
            if (live.getEndDate().isBefore(now)) {
                live.setStatus(LiveStatus.FINISHED);
            }
        });
        liveRepository.saveAll(livesList);
        return new ResponseDTO(HttpStatus.OK, "Lives status updated");
    }

    public ResponseStatusException liveNotFound() {
        return liveNotFound(null);
    }

    public ResponseStatusException liveNotFound(Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Live not found: " + id.toString());
    }

    public ResponseStatusException incorrectDate() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date parameters");
    }

    public ResponseDTO updateLive(LiveDTO liveDTO) {
        if(liveDTO.getId() == null || Boolean.FALSE.equals(liveRepository.existsById(liveDTO.getId()))) {
            throw liveNotFound();
        }

        liveRepository.save(liveMapper.toEntity(liveDTO));
        return new ResponseDTO(HttpStatus.OK, "Live updated", liveDTO.getId().toString());
    }
}
