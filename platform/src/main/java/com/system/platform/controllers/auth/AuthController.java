package com.system.platform.controllers.auth;

import com.system.platform.dto.auth.UsuarioDTO;
import com.system.platform.entities.auth.Usuario;
import com.system.platform.services.auth.CookieService;
import com.system.platform.services.auth.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService authService;
    private final CookieService cookieService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO.Response> register(@Valid @RequestBody UsuarioDTO.Create data) {
        Usuario nuevoUsuario = this.authService.register(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDTO.Response.fromEntity(nuevoUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO.Response> login(@Valid @RequestBody UsuarioDTO.Login data) {
        // 1. Autenticar y generar token
        String token = this.authService.login(data);

        // 2. Crear cookie HttpOnly con el token
        ResponseCookie jwtCookie = this.cookieService.createJwtCookie(token);

        // 3. Responder adjuntando la cabecera Set-Cookie
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Setear cookie con tiempo de vida cero para que el navegador la elimine
        ResponseCookie cleanCookie = this.cookieService.deleteJwtCookie();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, cleanCookie.toString())
                .build();
    }
}
