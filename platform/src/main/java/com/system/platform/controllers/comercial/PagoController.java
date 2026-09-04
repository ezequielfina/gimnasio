package com.system.platform.controllers.comercial;

import com.system.platform.controllers.CrudController;
import com.system.platform.dto.comercial.PagoDTO;
import com.system.platform.entities.comercial.Pago;
import com.system.platform.services.ICrudService;
import com.system.platform.services.comercial.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController extends CrudController<Pago, PagoDTO.Create, PagoDTO.Response> {
    private final PagoService pagoService;

    @Override
    public ResponseEntity<PagoDTO.Response> create(@Valid @RequestBody PagoDTO.Create data) {
        return super.create(data);
    }

    @Override
    protected ICrudService<Pago, PagoDTO.Create> getService() {
        return this.pagoService;
    }

    @Override
    protected PagoDTO.Response toResponse(Pago entity) {
        return PagoDTO.Response.fromEntity(entity);
    }
}
