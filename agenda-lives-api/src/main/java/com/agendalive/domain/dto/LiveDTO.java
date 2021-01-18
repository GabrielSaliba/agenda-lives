package com.agendalive.domain.dto;

import com.agendalive.domain.enums.LiveStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class LiveDTO {

    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    String channel;

    @NotEmpty
    String liveUrl;

    @NotNull
    LocalDateTime startDate;

    @NotNull
    LocalDateTime endDate;

    LiveStatus status;

}
