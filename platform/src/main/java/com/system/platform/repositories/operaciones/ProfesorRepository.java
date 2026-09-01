package com.system.platform.repositories.operaciones;

import com.system.platform.entities.operaciones.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfesorRepository extends JpaRepository<Profesor, UUID> {
}
