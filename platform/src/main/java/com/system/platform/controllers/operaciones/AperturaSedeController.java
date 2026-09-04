package com.system.platform.controllers.operaciones;

import com.system.platform.dto.operaciones.AperturaSedeDTO;
import com.system.platform.entities.operaciones.AperturaSede;
import com.system.platform.services.operaciones.AperturaSedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aperturas")
public class AperturaSedeController {
    private final AperturaSedeService aperturaSedeService;

    @PostMapping
    public ResponseEntity<AperturaSedeDTO.Response> create(@RequestBody AperturaSedeDTO.Create data) {
        AperturaSede aperturaSede = this.aperturaSedeService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                AperturaSedeDTO.Response.fromEntity(aperturaSede)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AperturaSedeDTO.Response> getById(@PathVariable UUID id) {
        AperturaSede aperturaSede = this.aperturaSedeService.readById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                AperturaSedeDTO.Response.fromEntity(aperturaSede)
        );
    }

    @GetMapping
    public ResponseEntity<List<AperturaSedeDTO.Response>> getAll() {
        List<AperturaSede> aperturaSedes = this.aperturaSedeService.readAll();
        List<AperturaSedeDTO.Response> listaDTO = new ArrayList<>();

        for(AperturaSede as : aperturaSedes) {
            listaDTO.add(AperturaSedeDTO.Response.fromEntity(as));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.aperturaSedeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
