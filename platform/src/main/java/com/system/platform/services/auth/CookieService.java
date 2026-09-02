package com.system.platform.services.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    public static final String COOKIE_NAME = "jwt_token";

    @Value("${jwt.expiration-ms:86400000}")
    private long expirationMs;

    // Crea la cookie con el token para setear en el login
    public ResponseCookie createJwtCookie(String token) {
        return ResponseCookie.from(COOKIE_NAME, token)
                .httpOnly(true)       // Evita acceso por JavaScript (protección contra XSS)
                .secure(false)        // Cambiar a true si usás HTTPS en producción
                .path("/")            // Disponible para todos los endpoints
                .maxAge(Duration.ofMillis(this.expirationMs))
                .sameSite("Lax")      // Protección básica contra CSRF
                .build();
    }

    // Crea una cookie vacía con tiempo cero para el logout
    public ResponseCookie deleteJwtCookie() {
        return ResponseCookie.from(COOKIE_NAME, "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
    }

    // Extrae el token leyendo las cookies del request entrante
    public Optional<String> extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(request.getCookies())
                .filter(c -> COOKIE_NAME.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }
}