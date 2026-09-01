package com.system.platform.repositories.operaciones;

import com.system.platform.entities.operaciones.Clase;
import com.system.platform.entities.operaciones.Espacio;
import com.system.platform.entities.operaciones.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface SesionRepository extends JpaRepository<Sesion, UUID> {
    boolean existsByClaseAndEspacioAndFecha(Clase clase, Espacio espacio, LocalDate fecha);
}
