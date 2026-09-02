package com.system.platform.controllers.geo;

import com.system.platform.dto.geo.PartidoDTO;
import com.system.platform.entities.geo.Partido;
import com.system.platform.services.geo.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/geo/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    @PostMapping
    @PreAuthorize("hasRole('SA')")
    public ResponseEntity<PartidoDTO.Response> create(@Valid @RequestBody PartidoDTO.Create data) {
        Partido partido = this.partidoService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(PartidoDTO.Response.fromEntity(partido));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN', 'SA')")
    public ResponseEntity<PartidoDTO.Response> getById(@PathVariable UUID id) {
        Partido partido = this.partidoService.readById(id);

        return ResponseEntity.status(HttpStatus.OK).body(PartidoDTO.Response.fromEntity(partido));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN', 'SA')")
    public ResponseEntity<List<PartidoDTO.Response>> getAll() {
        List<Partido> partidos = this.partidoService.readAll();
        List<PartidoDTO.Response> listaDTO = new ArrayList<>();

        for (Partido p : partidos) {
            listaDTO.add(PartidoDTO.Response.fromEntity(p));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('SA')")
    public ResponseEntity<PartidoDTO.Response> update(@PathVariable UUID id, @Valid @RequestBody PartidoDTO.Update data) {
        Partido partido = this.partidoService.update(id, data);

        return ResponseEntity.status(HttpStatus.OK).body(PartidoDTO.Response.fromEntity(partido));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SA')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.partidoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}