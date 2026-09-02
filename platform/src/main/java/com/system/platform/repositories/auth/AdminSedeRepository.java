package com.system.platform.repositories.auth;

import com.system.platform.entities.auth.AdminSede;
import com.system.platform.entities.auth.Usuario;
import com.system.platform.entities.operaciones.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminSedeRepository extends JpaRepository<AdminSede, UUID> {
    boolean existsByAdminAndSede(Usuario admin, Sede sede);
}
