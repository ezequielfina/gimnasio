package com.system.platform.repositories;

import com.system.platform.entities.operaciones.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EspacioRepository extends JpaRepository<Espacio, UUID> {
}
