package com.system.platform.repositories;

import com.system.platform.entities.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalidadRepository extends JpaRepository<Localidad, UUID> {
}
