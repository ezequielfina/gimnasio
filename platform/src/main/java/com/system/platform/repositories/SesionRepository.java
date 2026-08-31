package com.system.platform.repositories;

import com.system.platform.entities.operaciones.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SesionRepository extends JpaRepository<Sesion, UUID> {
}
