package com.system.platform.repositories;

import com.system.platform.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
}
