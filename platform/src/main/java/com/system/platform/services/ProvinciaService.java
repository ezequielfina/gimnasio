package com.system.platform.services;

import com.system.platform.dto.ProvinciaCreateDTO;
import com.system.platform.entities.Provincia;
import com.system.platform.repositories.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinciaService {
    private final ProvinciaRepository provinciaRepository;

    public Provincia create(ProvinciaCreateDTO data) {
        Provincia provincia = Provincia.builder()
                .provincia(data.provincia()).build();
        return provinciaRepository.save(provincia);
    }

}
