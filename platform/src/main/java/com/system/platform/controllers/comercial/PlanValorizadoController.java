package com.system.platform.controllers.comercial;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.comercial.PlanValorizadoDTO;
import com.system.platform.entities.comercial.PlanValorizado;
import com.system.platform.services.ICrudService;
import com.system.platform.services.comercial.PlanValorizadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planes_valorizados")
@RequiredArgsConstructor
public class PlanValorizadoController extends CrudController
        <
            PlanValorizado,
            PlanValorizadoDTO.Create,
            PlanValorizadoDTO.Response
        > {
    private final PlanValorizadoService planValorizadoService;

    @Override
    protected ICrudService<PlanValorizado, PlanValorizadoDTO.Create> getService() {
        return this.planValorizadoService;
    }

    @Override
    protected PlanValorizadoDTO.Response toResponse(PlanValorizado entity) {
        return PlanValorizadoDTO.Response.fromEntity(entity);
    }
}
