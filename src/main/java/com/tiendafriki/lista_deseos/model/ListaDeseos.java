package com.tiendafriki.lista_deseos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "wishlist")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 12, unique = true)
    private String rut;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Detalle> detalle;
}