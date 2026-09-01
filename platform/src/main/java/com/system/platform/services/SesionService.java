package com.system.platform.services;

import com.system.platform.common.exception.DataIntegrityViolationException;
import com.system.platform.dto.SesionDTO;
import com.system.platform.entities.operaciones.Clase;
import com.system.platform.entities.operaciones.Espacio;
import com.system.platform.entities.operaciones.Sesion;
import com.system.platform.repositories.SesionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SesionService {
    private final SesionRepository sesionRepository;
    private final ClaseService claseService;
    private final EspacioService espacioService;

    public Sesion create(SesionDTO.Create data) {
        Clase clase = this.claseService.readById(data.clase().id());
        Espacio espacio = this.espacioService.readById(data.espacio().id());

        boolean b = this.sesionRepository.existsByClaseAndEspacioAndFecha(
                clase,
                espacio,
                data.fecha()
        );

        if (b) {
            throw new DataIntegrityViolationException("Viola reglas de negocio");
        }

        Sesion sesion = Sesion.builder()
                .clase(clase)
                .espacio(espacio)
                .fecha(data.fecha())
                .estado(data.estadoSesion())
                .build();

        return this.sesionRepository.save(sesion);
    }

    public Sesion readById(UUID id) {
        return this.sesionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sesion no encontrada con ID " + id)
        );
    }

    public List<Sesion> readAll() {
        return this.sesionRepository.findAll();
    }

    //TERMINAR!!!!!!!!!!
    //UPDATE

    public void delete(UUID id) {
        Sesion sesion = this.readById(id);
        this.sesionRepository.delete(sesion);
    }
}
