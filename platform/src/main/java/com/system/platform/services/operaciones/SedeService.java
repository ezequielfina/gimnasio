package com.system.platform.services.operaciones;

import com.system.platform.dto.operaciones.SedeDTO;
import com.system.platform.entities.geo.Localidad;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.repositories.operaciones.SedeRepository;
import com.system.platform.services.geo.LocalidadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SedeService {
    private final SedeRepository sedeRepository;
    private final LocalidadService localidadService;

    public Sede create(SedeDTO.Create data) {
        Localidad localidad = this.localidadService.readById(data.localidad().id());

        Sede sede = Sede.builder()
                .nombre(data.nombre())
                .tipoSede(data.tipoSede())
                .isEnabled(data.isEnabled())
                .localidad(localidad)
                .direccion(data.direccion())
                .build();

        return this.sedeRepository.save(sede);
    }

    public Sede readById(UUID id) {
        return this.sedeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sede no encontrada con id" + id)
            );
    }

    public List<Sede> readAll() {
        return this.sedeRepository.findAll();
    }

    /*
    public Sede update(SedeDTO.Update) {
        return pass
   }
    */

    public void delete(UUID id) {
        Sede sede = this.readById(id);
        this.sedeRepository.delete(sede);
    }
}
