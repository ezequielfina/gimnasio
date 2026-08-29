package com.system.platform.controllers;

import com.system.platform.dto.LocalidadDTO;
import com.system.platform.entities.geo.Localidad;
import com.system.platform.services.LocalidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/geo/localidades")
public class LocalidadController {
    private final LocalidadService localidadService;

    @PostMapping
    public ResponseEntity<LocalidadDTO.Response> create(@Valid @RequestBody LocalidadDTO.Create data) {
        Localidad localidad = this.localidadService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(LocalidadDTO.Response.fromEntity(localidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalidadDTO.Response> getById(@PathVariable UUID id) {
        Localidad localidad = this.localidadService.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(LocalidadDTO.Response.fromEntity(localidad));
    }

    @GetMapping
    public ResponseEntity<List<LocalidadDTO.Response>> getAll() {
        List<Localidad> localidades = this.localidadService.readAll();
        List<LocalidadDTO.Response> listaDTO = new ArrayList<>();

        for (Localidad localidad : localidades) {
            listaDTO.add(LocalidadDTO.Response.fromEntity(localidad));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LocalidadDTO.Response> update(@PathVariable UUID id, @Valid @RequestBody LocalidadDTO.Update data) {
        Localidad localidad = this.localidadService.update(id, data);
        return ResponseEntity.status(HttpStatus.OK).body(LocalidadDTO.Response.fromEntity(localidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.localidadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
