package com.system.platform.controllers.operaciones;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.operaciones.ClaseDTO;
import com.system.platform.entities.operaciones.Clase;
import com.system.platform.services.ICrudService;
import com.system.platform.services.operaciones.ClaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clases")
public class ClaseController extends CrudController<Clase, ClaseDTO.Create, ClaseDTO.Response> {
    private final ClaseService claseService;

    @Override
    protected ICrudService<Clase, ClaseDTO.Create> getService() {
        return this.claseService;
    }

    @Override
    protected ClaseDTO.Response toResponse(Clase entity) {
        return ClaseDTO.Response.fromEntity(entity);
    }
}
