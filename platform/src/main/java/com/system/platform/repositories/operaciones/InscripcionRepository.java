package com.system.platform.repositories.operaciones;

import com.system.platform.entities.auth.Usuario;
import com.system.platform.entities.operaciones.Inscripcion;
import com.system.platform.entities.operaciones.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InscripcionRepository extends JpaRepository<Inscripcion, UUID> {
    boolean existsByUsuarioAndSesion(Usuario usuario, Sesion sesion);
}
