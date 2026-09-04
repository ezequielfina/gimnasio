package com.system.platform.controllers.operaciones;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.operaciones.SedeDTO;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.services.ICrudService;
import com.system.platform.services.operaciones.SedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sedes")
@RequiredArgsConstructor
public class SedeController extends CrudController<Sede, SedeDTO.Create, SedeDTO.Response> {
    private final SedeService sedeService;

    @Override
    protected ICrudService<Sede, SedeDTO.Create> getService() {
        return this.sedeService;
    }

    @Override
    protected SedeDTO.Response toResponse(Sede entity) {
        return SedeDTO.Response.fromEntity(entity);
    }

}
