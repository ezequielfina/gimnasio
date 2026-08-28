package com.system.platform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "localidades", schema = "geo")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Localidad extends EntityBase{

    @Column(name = "localidad", nullable = false, length = 100)
    private String localidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

}
