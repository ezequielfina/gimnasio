package com.system.platform.services.comercial;

import com.system.platform.common.exception.ReglaNegocioException;
import com.system.platform.dto.comercial.PagoDTO;
import com.system.platform.entities.comercial.EstadoPago;
import com.system.platform.entities.comercial.Membresia;
import com.system.platform.entities.comercial.Pago;
import com.system.platform.repositories.comercial.PagoRepository;
import com.system.platform.services.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoService implements ICrudService<Pago, PagoDTO.Create> {
    private final PagoRepository pagoRepository;
    private final MembresiaService membresiaService;

    @Override
    public Pago create(PagoDTO.Create data) {
        Membresia membresia = this.membresiaService.readById(data.membresia().id());
        Optional<Pago> pagoExistente = this.pagoRepository.findByMembresia(membresia);

        // Existe pago a membresía asociada, pero el estado NO está rechazado
        if (pagoExistente.isPresent() && !EstadoPago.RECHAZADO.equals(pagoExistente.get().getEstado())) {
            throw new ReglaNegocioException("Ya existe un pago asociado a esa membresía el cual no se encuentra rechazado.");
        }

        Pago pagoNuevo = Pago.builder()
                .membresia(membresia)
                .monto(data.monto())
                .metodo(data.metodo())
                .estado(data.estadoPago())
                .fechaPago(data.fechaPago())
                .transaccionId(data.transaccionId())
                .build();

        return this.pagoRepository.save(pagoNuevo);
    }

    @Override
    public Pago readById(UUID id) {
        return this.pagoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pago no encontrado con ID " + id)
        );
    }

    @Override
    public List<Pago> readAll() {
        return this.pagoRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        Pago pago = this.readById(id);
        this.pagoRepository.delete(pago);
    }
}
