package com.system.platform.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "provincias", schema = "geo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Provincia extends EntityBase {
    @Column(name = "provincia", nullable = false, length = 75)
    private String nombre;

}
