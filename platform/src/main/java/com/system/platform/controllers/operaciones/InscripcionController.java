package com.system.platform.controllers.operaciones;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.operaciones.InscripcionDTO;
import com.system.platform.entities.operaciones.Inscripcion;
import com.system.platform.services.ICrudService;
import com.system.platform.services.operaciones.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inscripciones")
public class InscripcionController extends CrudController<Inscripcion, InscripcionDTO.Create, InscripcionDTO.Response> {
    private final InscripcionService inscripcionService;

    @Override
    protected ICrudService<Inscripcion, InscripcionDTO.Create> getService() {
        return this.inscripcionService;
    }

    @Override
    protected InscripcionDTO.Response toResponse(Inscripcion entity) {
        return InscripcionDTO.Response.fromEntity(entity);
    }
}
