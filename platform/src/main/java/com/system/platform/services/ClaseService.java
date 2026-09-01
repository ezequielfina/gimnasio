package com.system.platform.services;

import com.system.platform.dto.ClaseDTO;
import com.system.platform.entities.operaciones.Clase;
import com.system.platform.entities.operaciones.Disciplina;
import com.system.platform.entities.operaciones.Profesor;
import com.system.platform.repositories.ClaseRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClaseService {
    private final ClaseRespository claseRespository;
    private final DisciplinaService disciplinaService;
    private final ProfesorService profesorService;

    public Clase create(ClaseDTO.Create data) {
        Disciplina disciplina = this.disciplinaService.readById(data.disciplina().id());
        Profesor profesor = this.profesorService.readById(data.profesor().id());

        Clase clase = Clase.builder()
                .disciplina(disciplina)
                .profesor(profesor)
                .dia(data.dia())
                .horarioInicio(data.horarioInicio())
                .duracionMinutos(data.duracionMinutos())
                .build();

        return this.claseRespository.save(clase);
    }

    public Clase readById(UUID id) {
        return this.claseRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Clase no encontrada con ID " + id)
        );
    }

    public List<Clase> readAll() {
        return this.claseRespository.findAll();
    }

    // TERMINAR!!!!!!!!!
    // UPDATE

    public void delete(UUID id) {
        Clase clase = this.readById(id);
        this.claseRespository.delete(clase);
    }
}