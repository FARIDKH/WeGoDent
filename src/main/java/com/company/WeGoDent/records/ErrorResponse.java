package com.company.WeGoDent.records;
import java.time.Instant;

public record ErrorResponse(
        String code,
        String message,
        Instant timestamp
) {}