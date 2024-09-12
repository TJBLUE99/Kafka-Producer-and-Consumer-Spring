package com.notifications.streaming.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class ApiResponse {
    public ApiResponse(Object message, int status) {
        this.message = message;
        this.status = status;
    }

    private Object message;
    private int status;
}