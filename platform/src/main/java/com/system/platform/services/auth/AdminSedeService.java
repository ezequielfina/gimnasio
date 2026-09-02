package com.system.platform.services.auth;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.auth.AdminSedeDTO;
import com.system.platform.entities.auth.AdminSede;
import com.system.platform.entities.auth.Usuario;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.repositories.auth.AdminSedeRepository;
import com.system.platform.services.operaciones.SedeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminSedeService {
    private final AdminSedeRepository adminSedeRepository;
    private final UsuarioService usuarioService;
    private final SedeService sedeService;

    public AdminSede create (AdminSedeDTO.Create data) {
        Usuario usuario = this.usuarioService.readById(data.usuario().id());
        Sede sede = this.sedeService.readById(data.sede().id());

        boolean b = this.adminSedeRepository.existsByAdminAndSede(usuario, sede);
        if (b) {
            throw new RecursoDuplicadoException("Ya existe un registro con ese usuario y sede");
        }

        AdminSede adminSede = AdminSede.builder()
                .admin(usuario)
                .sede(sede)
                .build();

        return this.adminSedeRepository.save(adminSede);
    }

    public AdminSede readById(UUID id) {
        return this.adminSedeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("AdminSede no encontrado con ID " + id)
        );
    }

    public void delete(UUID id) {
        AdminSede adminSede = this.readById(id);
        this.adminSedeRepository.delete(adminSede);
    }
}
