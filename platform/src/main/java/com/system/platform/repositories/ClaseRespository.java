package com.system.platform.repositories;

import com.system.platform.entities.operaciones.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClaseRespository extends JpaRepository<Clase, UUID> {
}
