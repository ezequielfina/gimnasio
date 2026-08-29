package com.system.platform.entities.geo;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "localidades", schema = "geo")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Localidad extends EntityBase {

    @Column(name = "localidad", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

}
