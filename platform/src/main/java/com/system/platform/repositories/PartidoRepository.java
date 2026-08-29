package com.system.platform.repositories;

import com.system.platform.entities.geo.Partido;
import com.system.platform.entities.geo.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartidoRepository extends JpaRepository<Partido, UUID> {
    boolean existsByNombreAndProvincia(String nombre, Provincia provincia);
}
