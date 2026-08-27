package com.system.platform.repositories;

import com.system.platform.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartidoRepository extends JpaRepository<Partido, UUID> {
}
