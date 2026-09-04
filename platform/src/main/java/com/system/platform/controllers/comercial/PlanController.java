package com.system.platform.controllers.comercial;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.comercial.PlanDTO;
import com.system.platform.entities.comercial.Plan;
import com.system.platform.services.ICrudService;
import com.system.platform.services.comercial.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlanController extends CrudController<Plan, PlanDTO.Create, PlanDTO.Response> {
    private final PlanService planService;

    @Override
    protected ICrudService<Plan, PlanDTO.Create> getService() {
        return this.planService;
    }

    @Override
    protected PlanDTO.Response toResponse(Plan entity) {
        return PlanDTO.Response.fromEntity(entity);
    }
}
