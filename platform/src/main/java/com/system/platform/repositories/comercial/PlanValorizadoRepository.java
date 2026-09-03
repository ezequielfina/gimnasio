package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.PlanValorizado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanValorizadoRepository extends JpaRepository<PlanValorizado, UUID> {
}
