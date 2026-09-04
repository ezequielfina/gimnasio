package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
    boolean existsByNombre(String nombre);
}
