package com.system.platform.repositories.operaciones;

import com.system.platform.entities.operaciones.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SedeRepository extends JpaRepository<Sede, UUID> {
}
