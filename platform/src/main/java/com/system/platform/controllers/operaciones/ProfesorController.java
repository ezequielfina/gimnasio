package com.system.platform.controllers.operaciones;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.operaciones.ProfesorDTO;
import com.system.platform.entities.operaciones.Profesor;
import com.system.platform.services.ICrudService;
import com.system.platform.services.operaciones.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/profes")
public class ProfesorController extends CrudController<Profesor, ProfesorDTO.Create, ProfesorDTO.Response> {
    private final ProfesorService profesorService;

    @Override
    protected ICrudService<Profesor, ProfesorDTO.Create> getService() {
        return this.profesorService;
    }

    @Override
    protected ProfesorDTO.Response toResponse(Profesor entity) {
        return ProfesorDTO.Response.fromEntity(entity);
    }
}
