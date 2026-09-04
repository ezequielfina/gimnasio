package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.Membresia;
import com.system.platform.entities.comercial.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {
    Optional<Pago> findByMembresia(Membresia membresia);
}
