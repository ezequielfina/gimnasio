package com.system.platform.services.operaciones;

import com.system.platform.dto.operaciones.EspacioDTO;
import com.system.platform.entities.operaciones.Espacio;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.repositories.operaciones.EspacioRepository;
import com.system.platform.services.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EspacioService implements ICrudService<Espacio, EspacioDTO.Create> {
    private final EspacioRepository espacioRepository;
    private final SedeService sedeService;

    public Espacio create(EspacioDTO.Create data) {
        Sede sede = this.sedeService.readById(data.sede().id());

        Espacio espacio = Espacio.builder()
                .nombre(data.nombre())
                .cupoMaximo(data.cupoMaximo())
                .sede(sede)
                .build();

        return this.espacioRepository.save(espacio);
    }

    public Espacio readById(UUID id) {
        return this.espacioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Espacio no encontrado con ID " + id)
        );
    }

    public List<Espacio> readAll() {
        return this.espacioRepository.findAll();
    }

    // TERMINAR!!!!!!!!!!!!!
    // public update

    public void deleteById(UUID id) {
        Espacio espacio = this.readById(id);
        this.espacioRepository.delete(espacio);
    }
}
