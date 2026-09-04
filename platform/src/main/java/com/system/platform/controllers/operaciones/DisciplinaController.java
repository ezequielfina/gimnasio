package com.system.platform.controllers.operaciones;

import com.system.platform.dto.operaciones.DisciplinaDTO;
import com.system.platform.entities.operaciones.Disciplina;
import com.system.platform.services.operaciones.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/disciplinas")
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<DisciplinaDTO.Response> create(@RequestBody DisciplinaDTO.Create data) {
        Disciplina disciplina = this.disciplinaService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                DisciplinaDTO.Response.fromEntity(disciplina)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO.Response> getById(@PathVariable UUID id) {
        Disciplina disciplina = this.disciplinaService.readById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                DisciplinaDTO.Response.fromEntity(disciplina)
        );
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO.Response>> getAll() {
        List<Disciplina> disciplinas = this.disciplinaService.readAll();
        List<DisciplinaDTO.Response> listaDTO = new ArrayList<>();

        for (Disciplina d : disciplinas) {
            listaDTO.add(
                    DisciplinaDTO.Response.fromEntity(d)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.disciplinaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
