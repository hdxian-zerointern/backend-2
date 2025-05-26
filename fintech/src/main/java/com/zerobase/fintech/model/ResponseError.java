package com.zerobase.fintech.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseError {
    private String message;

    public static ResponseError of(FieldError e) {
       return ResponseError.builder()
                .message(e.getDefaultMessage())
                .build();
    }
}
