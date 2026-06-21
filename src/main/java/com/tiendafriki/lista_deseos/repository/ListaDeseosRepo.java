package com.tiendafriki.lista_deseos.repository;

import com.tiendafriki.lista_deseos.model.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaDeseosRepo extends JpaRepository<ListaDeseos, Integer> {

    Optional<ListaDeseos> findByRut(String rut);

}