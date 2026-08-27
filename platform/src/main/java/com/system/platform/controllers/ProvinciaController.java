package com.system.platform.controllers;

import com.system.platform.dto.ProvinciaCreateDTO;
import com.system.platform.dto.ProvinciaResponseDTO;
import com.system.platform.entities.Provincia;
import com.system.platform.services.ProvinciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/geo/provincias")
public class ProvinciaController {
    private final ProvinciaService provinciaService;

    @PostMapping()
    public ResponseEntity<ProvinciaResponseDTO> create(@RequestBody ProvinciaCreateDTO data) {
        Provincia provincia = provinciaService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProvinciaResponseDTO.fromEntity(provincia));
    }
}
