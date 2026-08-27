package com.system.platform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "provincias", schema = "geo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "provincia", nullable = false, length = 75)
    private String provincia;

}
