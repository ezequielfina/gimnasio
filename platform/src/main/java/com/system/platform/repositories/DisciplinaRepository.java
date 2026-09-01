package com.system.platform.repositories;

import com.system.platform.entities.operaciones.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {
    boolean existsByNombre(String nombre);
}
