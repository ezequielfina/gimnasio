package com.system.platform.repositories.operaciones;

import com.system.platform.entities.operaciones.AperturaSede;
import com.system.platform.entities.operaciones.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AperturaSedeRepository extends JpaRepository<AperturaSede, UUID> {
    boolean existsBySede(Sede sede);
}
