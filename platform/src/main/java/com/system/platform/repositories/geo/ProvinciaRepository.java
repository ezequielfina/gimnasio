package com.system.platform.repositories.geo;

import com.system.platform.entities.geo.Provincia;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
    boolean existsById(@NonNull UUID id);
    boolean existsByNombre(String nombre);
}
