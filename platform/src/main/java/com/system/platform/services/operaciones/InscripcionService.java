package com.system.platform.services.operaciones;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.operaciones.InscripcionDTO;
import com.system.platform.entities.auth.Usuario;
import com.system.platform.entities.operaciones.Inscripcion;
import com.system.platform.entities.operaciones.Sesion;
import com.system.platform.repositories.operaciones.InscripcionRepository;
import com.system.platform.services.ICrudService;
import com.system.platform.services.auth.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscripcionService implements ICrudService<Inscripcion, InscripcionDTO.Create> {
    private final InscripcionRepository inscripcionRepository;
    private final UsuarioService usuarioService;
    private final SesionService sesionService;

    @Override
    public Inscripcion create(InscripcionDTO.Create data) {
        Usuario usuario = this.usuarioService.readById(data.usuario().id());
        Sesion sesion = this.sesionService.readById(data.sesion().id());

        boolean b = this.inscripcionRepository.existsByUsuarioAndSesion(usuario, sesion);
        if (b) {
            throw new RecursoDuplicadoException("Ya existe un registro con ese usuario y sesión.");
        }

        Inscripcion inscripcion = Inscripcion.builder()
                .usuario(usuario)
                .sesion(sesion)
                .asistio(false)
                .build();

        return this.inscripcionRepository.save(inscripcion);
    }

    @Override
    public Inscripcion readById(UUID id) {
        return this.inscripcionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Inscripción no fue encontrada con ID " + id)
        );
    }

    @Override
    public List<Inscripcion> readAll() {
        return this.inscripcionRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        Inscripcion inscripcion = this.readById(id);
        this.inscripcionRepository.delete(inscripcion);
    }
}
