package com.softserveinc.ita.javaclub.volleyblog.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
}
