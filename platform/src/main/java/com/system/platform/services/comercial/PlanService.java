package com.system.platform.services.comercial;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.comercial.PlanDTO;
import com.system.platform.entities.comercial.Plan;
import com.system.platform.repositories.comercial.PlanRepository;
import com.system.platform.services.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanService implements ICrudService<Plan, PlanDTO.Create> {
    private final PlanRepository planRepository;

    @Override
    public Plan create(PlanDTO.Create data) {
        boolean b = this.planRepository.existsByNombre(data.nombre());
        if (b) {
            throw new RecursoDuplicadoException("Ya existe un plan con nombre " + data.nombre());
        }

        Plan planPadre = null;
        if (data.planPadre() != null) {
             planPadre = this.readById(data.planPadre().id());
        }

        Plan plan = Plan.builder()
                .nombre(data.nombre())
                .isEnabled(data.isEnabled())
                .planPadre(planPadre)
                .permisos(data.permisos())
                .build();

        return this.planRepository.save(plan);
    }

    @Override
    public Plan readById(UUID id) {
        return this.planRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Plan no encontrado con ID " + id)
        );
    }

    @Override
    public List<Plan> readAll() {
        return this.planRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        Plan plan = this.readById(id);
        this.planRepository.delete(plan);
    }
}
