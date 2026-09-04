package com.system.platform.services.operaciones;

import com.system.platform.dto.operaciones.ProfesorDTO;
import com.system.platform.entities.operaciones.Profesor;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.repositories.operaciones.ProfesorRepository;
import com.system.platform.services.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfesorService implements ICrudService<Profesor, ProfesorDTO.Create> {
    private final ProfesorRepository profesorRepository;
    private final SedeService sedeService;

    public Profesor create(ProfesorDTO.Create data) {
        Sede sede = this.sedeService.readById(data.sede().id());
        Profesor profesor = Profesor.builder()
                .sede(sede)
                .nombre(data.nombre())
                .apellido(data.apellido())
                .build();

        return this.profesorRepository.save(profesor);
    }

    public Profesor readById(UUID id) {
        return this.profesorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Profesor no encontrado con ID " + id)
        );
    }

    public List<Profesor> readAll() {
        return this.profesorRepository.findAll();
    }

    // TERMINAR!!!!!!!!!!!!!!
    public Profesor update() {
        return new Profesor();
    }

    public void deleteById(UUID id) {
        Profesor profesor = this.readById(id);
        this.profesorRepository.delete(profesor);
    }
}
