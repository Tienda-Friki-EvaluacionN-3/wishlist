package com.tiendafriki.lista_deseos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    @JsonBackReference
    private ListaDeseos wishlist;

    @Column(nullable = false)
    private Integer productoID;

    @Column(nullable = false, length = 150)
    private String producto;

    @Column(nullable = false)
    private Integer precio;
}