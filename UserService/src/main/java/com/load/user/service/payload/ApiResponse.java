package com.load.user.service.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;
    
}
