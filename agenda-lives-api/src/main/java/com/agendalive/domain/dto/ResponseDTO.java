package com.agendalive.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {

    HttpStatus status;
    String message;
    String value = "";

    public ResponseDTO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
