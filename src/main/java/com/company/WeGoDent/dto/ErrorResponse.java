package com.company.WeGoDent.dto;
import java.time.Instant;

public record ErrorResponse(
        String code,
        String message,
        Instant timestamp
) {}