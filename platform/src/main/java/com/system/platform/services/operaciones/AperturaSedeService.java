package com.system.platform.services.operaciones;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.operaciones.AperturaSedeDTO;
import com.system.platform.entities.operaciones.AperturaSede;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.repositories.operaciones.AperturaSedeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AperturaSedeService {
    private final AperturaSedeRepository aperturaSedeRepository;
    private final SedeService sedeService;

    public AperturaSede create(AperturaSedeDTO.Create data) {
        Sede sede = this.sedeService.readById(data.sede().id());

        boolean b = this.aperturaSedeRepository.existsBySede(sede);
        if (b) {
            throw new RecursoDuplicadoException("Ya existe entidad de apertura para sede con ID " + data.sede().id());
        }

        AperturaSede aperturaSede = AperturaSede.builder()
                .sede(sede)
                .dia(data.dia())
                .horarioApertura(data.horarioApertura())
                .horarioClausura(data.horarioClausura())
                .build();

        return this.aperturaSedeRepository.save(aperturaSede);
    }

    public AperturaSede readById(UUID id) {
        return this.aperturaSedeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Apertura sede no encontrada con ID " + id)
        );
    }

    public List<AperturaSede> readAll() {
        return this.aperturaSedeRepository.findAll();
    }

    // TERMINAR!!!!!!!!!!!!
    // UPDATE

    public void delete(UUID id) {
        AperturaSede aperturaSede = this.readById(id);
        this.aperturaSedeRepository.delete(aperturaSede);
    }
}
