package com.system.platform.controllers;

import com.system.platform.dto.ProvinciaDTO;
import com.system.platform.entities.Provincia;
import com.system.platform.services.ProvinciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/geo/provincias")
public class ProvinciaController {
    private final ProvinciaService provinciaService;

    @PostMapping
    public ResponseEntity<ProvinciaDTO.Response> create(@Valid @RequestBody ProvinciaDTO.Create data) {
        Provincia provincia = this.provinciaService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ProvinciaDTO.Response.fromEntity(provincia)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaDTO.Response> readById(@PathVariable UUID id) {
        Provincia provincia = this.provinciaService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                ProvinciaDTO.Response.fromEntity(provincia)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProvinciaDTO.Response> updateById(@PathVariable UUID id, @Valid @RequestBody ProvinciaDTO.Update data) {
        Provincia provincia = this.provinciaService.updateById(id, data);

        return ResponseEntity.status(HttpStatus.OK).body(
                ProvinciaDTO.Response.fromEntity(provincia)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.provinciaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
