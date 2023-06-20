package com.company.WeGoDent.records;

import jakarta.validation.constraints.NotNull;

public record GeoLocationInformation(@NotNull Double longitude, @NotNull Double latitude) {
}
