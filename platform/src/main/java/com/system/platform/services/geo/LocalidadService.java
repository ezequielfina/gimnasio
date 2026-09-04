package com.system.platform.services.geo;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.geo.LocalidadDTO;
import com.system.platform.entities.geo.Localidad;
import com.system.platform.entities.geo.Partido;
import com.system.platform.repositories.geo.LocalidadRepository;
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
public class LocalidadService implements ICrudService<Localidad, LocalidadDTO.Create> {

    private final LocalidadRepository localidadRepository;
    private final PartidoService partidoService;

    public Localidad create(LocalidadDTO.Create data) {
        Partido partido = this.partidoService.readById(data.partido().id());
        boolean b = this.localidadRepository.existsByNombreAndPartido(data.nombre(), partido);

        if (b) {
            throw new RecursoDuplicadoException(
                            "Ya existe una localidad con nombre " +
                            data.nombre() +
                            " y partido " +
                                    partido.getNombre()
            );
        }

        Localidad localidad = Localidad.builder()
                .nombre(data.nombre())
                .partido(partido)
                .build();

        return this.localidadRepository.save(localidad);
    }

    public Localidad readById(UUID id) {
        return this.localidadRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No fue encontrada localidad con ID " + id)
        );
    }

    public List<Localidad> readAll() {
        return this.localidadRepository.findAll();
    }

    public Localidad update(UUID id, LocalidadDTO.Update data) {
        // 1. Obtener la localidad existente mediante el método propio
        Localidad localidad = this.readById(id);

        // 2. Determinar los valores finales para evaluar si cambian
        String nuevoNombre = (data.nombre() != null && !data.nombre().isBlank())
                ? data.nombre()
                : localidad.getNombre();

        Partido nuevoPartido = localidad.getPartido();
        if (data.partido() != null && data.partido().id() != null) {
            nuevoPartido = this.partidoService.readById(data.partido().id());
        }

        // 3. Validar unicidad solo si cambió el nombre o el partido
        boolean cambioNombre = !nuevoNombre.equalsIgnoreCase(localidad.getNombre());
        boolean cambioPartido = !nuevoPartido.getId().equals(localidad.getPartido().getId());

        if (cambioNombre || cambioPartido) {
            boolean yaExiste = this.localidadRepository.existsByNombreAndPartido(nuevoNombre, nuevoPartido);
            if (yaExiste) {
                throw new RecursoDuplicadoException(
                        "Ya existe una localidad con nombre " + nuevoNombre +
                                " en el partido " + nuevoPartido.getNombre()
                );
            }
        }

        // 4. Aplicar cambios a la entidad administrada (dirty checking de Hibernate)
        localidad.setNombre(nuevoNombre);
        localidad.setPartido(nuevoPartido);

        return localidad;
    }

    public void deleteById(UUID id) {
        Localidad localidad = this.readById(id);
        this.localidadRepository.delete(localidad);
    }
}
