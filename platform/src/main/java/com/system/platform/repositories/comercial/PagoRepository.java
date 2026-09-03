package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {
}
