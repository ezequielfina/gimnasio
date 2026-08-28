package com.system.platform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "partidos", schema = "geo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partido extends EntityBase {

    @Column(name = "partido", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

}
