package com.system.platform.repositories;

import com.system.platform.entities.operaciones.AperturaSede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AperturaSedeRepository extends JpaRepository<AperturaSede, UUID> {
}
