package com.system.platform.controllers.comercial;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.comercial.MembresiaDTO;
import com.system.platform.entities.comercial.Membresia;
import com.system.platform.services.ICrudService;
import com.system.platform.services.comercial.MembresiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membresias")
@RequiredArgsConstructor
public class MembresiaController extends CrudController<Membresia, MembresiaDTO.Create, MembresiaDTO.Response> {
    private final MembresiaService membresiaService;

    @Override
    protected ICrudService<Membresia, MembresiaDTO.Create> getService() {
        return this.membresiaService;
    }

    @Override
    protected MembresiaDTO.Response toResponse(Membresia entity) {
        return MembresiaDTO.Response.fromEntity(entity);
    }
}
