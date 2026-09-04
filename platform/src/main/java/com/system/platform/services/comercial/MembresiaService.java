package com.system.platform.services.comercial;

import com.system.platform.common.exception.ReglaNegocioException;
import com.system.platform.dto.comercial.MembresiaDTO;
import com.system.platform.entities.auth.Usuario;
import com.system.platform.entities.comercial.Membresia;
import com.system.platform.entities.comercial.PlanValorizado;
import com.system.platform.repositories.comercial.MembresiaRepository;
import com.system.platform.services.ICrudService;
import com.system.platform.services.auth.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembresiaService implements ICrudService<Membresia, MembresiaDTO.Create> {
    private final MembresiaRepository membresiaRepository;
    private final UsuarioService usuarioService;
    private final PlanValorizadoService planValorizadoService;

    @Override
    public Membresia create(MembresiaDTO.Create data) {
        Usuario usuario = this.usuarioService.readById(data.usuario().id());
        PlanValorizado planValorizado = this.planValorizadoService.readById(data.planValorizado().id());

        List<Membresia> membresias = this.membresiaRepository.findByIdUsuarioConChoqueFechas(
                data.usuario().id(),
                data.planValorizado().id(),
                data.fechaCompra()
        );

        if (!membresias.isEmpty()) {
            throw new ReglaNegocioException("Ya existe membresía con fecha vigente");
        }

        Membresia membresia = Membresia.builder()
                .usuario(usuario)
                .planValorizado(planValorizado)
                .fechaCompra(data.fechaCompra())
                .fechaVencimiento(data.fechaVencimiento())
                .tipo(data.tipoPlan())
                .estado(data.estadoMembresia())
                .build();

        return this.membresiaRepository.save(membresia);
    }

    @Override
    public Membresia readById(UUID id) {
        return this.membresiaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Membresia no encontrada con ID " + id)
        );
    }

    @Override
    public List<Membresia> readAll() {
        return this.membresiaRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        Membresia membresia = this.readById(id);
        this.membresiaRepository.delete(membresia);
    }
}
