package com.system.platform.repositories;

import com.system.platform.entities.operaciones.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InscripcionRepository extends JpaRepository<Inscripcion, UUID> {
}
