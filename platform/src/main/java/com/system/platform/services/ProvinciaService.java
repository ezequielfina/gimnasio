package com.system.platform.services;

import com.system.platform.common.exception.DataIntegrityViolationException;
import com.system.platform.dto.ProvinciaDTO;
import com.system.platform.entities.Provincia;
import com.system.platform.repositories.ProvinciaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProvinciaService {
    private final ProvinciaRepository provinciaRepository;

    @Transactional
    public Provincia create(ProvinciaDTO.Create data) throws DataIntegrityViolationException {
        boolean b = this.provinciaRepository.existsByNombre(data.nombre());
        if (b) {
            throw new DataIntegrityViolationException("Ya existe entidad provincia con nombre " + data.nombre());
        }

        Provincia provincia = Provincia.builder()
                .nombre(data.nombre()).build();
        return this.provinciaRepository.save(provincia);
    }

    @Transactional(readOnly = true)
    public Provincia getById(UUID id) {
        return this.provinciaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No fue encontrada provincia con id " + id)
        );
    }

    @Transactional
    public Provincia updateById(UUID id, ProvinciaDTO.Update data) {
        Provincia provincia = this.getById(id);
        provincia.setNombre(data.nombre());

        return this.provinciaRepository.save(provincia);
    }

    @Transactional
    public void deleteById(UUID id) {
        boolean b = this.provinciaRepository.existsById(id);
        if (!b) {
            throw new EntityNotFoundException("No fue encontrada la provincia con id " + id);
        }

        this.provinciaRepository.deleteById(id);
    }

}
