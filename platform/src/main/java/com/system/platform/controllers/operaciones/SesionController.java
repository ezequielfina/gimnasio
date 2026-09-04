package com.system.platform.controllers.operaciones;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.operaciones.SesionDTO;
import com.system.platform.entities.operaciones.Sesion;
import com.system.platform.services.ICrudService;
import com.system.platform.services.operaciones.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sesiones")
public class SesionController extends CrudController<Sesion, SesionDTO.Create, SesionDTO.Response> {
    private final SesionService sesionService;


    @Override
    protected ICrudService<Sesion, SesionDTO.Create> getService() {
        return this.sesionService;
    }

    @Override
    protected SesionDTO.Response toResponse(Sesion entity) {
        return SesionDTO.Response.fromEntity(entity);
    }
}
