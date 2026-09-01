package com.system.platform.services;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.DisciplinaDTO;
import com.system.platform.entities.operaciones.Disciplina;
import com.system.platform.repositories.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;

    public Disciplina create(DisciplinaDTO.Create data) {
        boolean b = this.disciplinaRepository.existsByNombre(data.nombre());

        if (b) {
            throw new RecursoDuplicadoException("Ya existe una disciplina " + data.nombre());
        }

        Disciplina disciplina = Disciplina.builder()
                .nombre(data.nombre())
                .descripcion(data.descripcion())
                .build();

        return this.disciplinaRepository.save(disciplina);
    }

    public Disciplina readById(UUID id) {
        return this.disciplinaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Disciplina no encontrada con ID " + id)
        );
    }

    public List<Disciplina> readAll() {
        return this.disciplinaRepository.findAll();
    }

    // TERMINAR!!!!!!!!!!!!!!
    // UPDATE

    public void delete(UUID id) {
        Disciplina disciplina = this.readById(id);
        this.disciplinaRepository.delete(disciplina);
    }
}
