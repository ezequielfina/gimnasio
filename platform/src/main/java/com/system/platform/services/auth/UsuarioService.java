package com.system.platform.services.auth;

import com.system.platform.common.exception.RecursoDuplicadoException;
import com.system.platform.dto.auth.UsuarioDTO;
import com.system.platform.entities.auth.Rol;
import com.system.platform.entities.auth.Usuario;
import com.system.platform.repositories.auth.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private Usuario register (UsuarioDTO.Create data, Rol rol) {
        boolean b_username = this.usuarioRepository.existsByUsername(data.username());
        if (b_username) {
            throw new RecursoDuplicadoException("Ya existe usuario con username: " + data.username());
        }

        boolean b_email = this.usuarioRepository.existsByEmail(data.email());
        if (b_email) {
            throw new RecursoDuplicadoException("Ya existe usuario con email: " + data.email());
        }

        Usuario usuario = Usuario.builder()
                .username(data.username())
                .email(data.email())
                .password(this.passwordEncoder.encode(data.password()))
                .rol(rol)
                .isEnabled(true)
                .build();

        return this.usuarioRepository.save(usuario);
    }

    public Usuario registerUsuario(UsuarioDTO.Create data) {
        return this.register(data, Rol.ROLE_USUARIO);
    }

    public Usuario registerAdmin(UsuarioDTO.Create data) {
        return this.register(data, Rol.ROLE_ADMIN);
    }

    public Usuario registerSa(UsuarioDTO.Create data) {
        return this.register(data, Rol.ROLE_SA);
    }

    public Usuario readById(UUID id) {
        return this.usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Usuario no encontrado con ID " + id)
        );
    }

    @Transactional(readOnly = true)
    public String login(UsuarioDTO.Login data) {
        // Valida usuario y contraseña contra la base de datos
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.username(), data.password())
        );

        // Si las credenciales son correctas, cargamos el usuario y generamos el token
        Usuario usuario = this.usuarioRepository.findByUsername(data.username())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + data.username()));

        return this.jwtService.generateToken(usuario);
    }

}
