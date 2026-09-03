package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MembresiaRepository extends JpaRepository<Membresia, UUID> {
}
