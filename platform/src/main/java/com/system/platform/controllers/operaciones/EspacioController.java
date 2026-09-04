package com.system.platform.controllers.operaciones;

import com.system.platform.dto.operaciones.EspacioDTO;
import com.system.platform.entities.operaciones.Espacio;
import com.system.platform.services.operaciones.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/espacios")
public class EspacioController {
    private final EspacioService espacioService;

    @PostMapping
    public ResponseEntity<EspacioDTO.Response> create (@RequestBody EspacioDTO.Create data) {
        Espacio espacio = this.espacioService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            EspacioDTO.Response.fromEntity(espacio)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacioDTO.Response> getById(@PathVariable UUID id) {
        Espacio espacio = this.espacioService.readById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
            EspacioDTO.Response.fromEntity(espacio)
        );
    }

    @GetMapping
    public ResponseEntity<List<EspacioDTO.Response>> getAll() {
        List<Espacio> espacios = this.espacioService.readAll();
        List<EspacioDTO.Response> listaDTO = new ArrayList<>();

        for (Espacio e : espacios) {
            listaDTO.add(
                    EspacioDTO.Response.fromEntity(e)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            listaDTO
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.espacioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
