package com.system.platform.entities.auth;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios", schema = "auth")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario extends EntityBase implements UserDetails {
    @Size(max = 30)
    @NotNull
    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 75)
    @NotNull
    @Column(name = "email", nullable = false, length = 75)
    private String email;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "rol", columnDefinition = "auth.roles not null")
    private Rol rol;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.rol.name()));
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.isEnabled);
    }
}
