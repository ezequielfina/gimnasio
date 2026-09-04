package com.system.platform.services.geo;

import com.system.platform.common.exception.DataIntegrityViolationException;
import com.system.platform.dto.geo.PartidoDTO;
import com.system.platform.entities.geo.Partido;
import com.system.platform.entities.geo.Provincia;
import com.system.platform.repositories.geo.PartidoRepository;
import com.system.platform.services.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PartidoService implements ICrudService<Partido, PartidoDTO.Create> {

    private final PartidoRepository partidoRepository;
    private final ProvinciaService provinciaService;

    public Partido create(PartidoDTO.Create data) {
        Provincia provincia = this.provinciaService.readById(data.provincia().id());

        boolean b = this.partidoRepository.existsByNombreAndProvincia(
                data.nombre(),
                provincia
        );

        if (b) {
            throw new DataIntegrityViolationException(
                    "Ya existe un partido con nombre " +
                    data.nombre() +
                    " y provincia " +
                    provincia.getNombre()
            );
        }

        Partido partido = Partido.builder()
                .nombre(data.nombre())
                .provincia(provincia)
                .build();

        return this.partidoRepository.save(partido);
    }

    public Partido readById(UUID id) {
        return this.partidoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Partido no encontrado con ID " + id)
        );
    }

    public List<Partido> readAll() {
        return this.partidoRepository.findAll();
    }

    public Partido update(UUID id, PartidoDTO.Update data) {
        // 1. Recuperamos la entidad
        Partido partido = this.readById(id);

        // 2. Chequeamos si el nombre no es nulo o está en blanco
        if (data.nombre() != null && !data.nombre().isBlank()) {
            partido.setNombre(data.nombre());
        }

        // 3. Actualizamos la relación con Provincia si viene en el payload
        if (data.provincia() != null && data.provincia().id() != null && data.provincia().id() != partido.getId()) {
            Provincia nuevaProvincia = this.provinciaService.readById(data.provincia().id());
            partido.setProvincia(nuevaProvincia);
        }

        // 4. Retornamos la entidad
        return partido;
    }

    public void deleteById(UUID id) {
        Partido partido = this.readById(id);
        this.partidoRepository.delete(partido);
    }
}
