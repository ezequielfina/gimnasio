package com.system.platform.dto;

import com.system.platform.entities.Provincia;

import java.util.UUID;

public record ProvinciaResponseDTO(
        UUID id,
        String provincia
) {
    public static ProvinciaResponseDTO fromEntity(Provincia provincia) {
        return new ProvinciaResponseDTO(
                provincia.getId(),
                provincia.getProvincia()
        );
    }
}
