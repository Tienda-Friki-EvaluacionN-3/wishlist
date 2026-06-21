package com.tiendafriki.lista_deseos.repository;

import com.tiendafriki.lista_deseos.model.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleRepo extends JpaRepository<Detalle, Integer> {

    List<Detalle> findByWishlist_Id(Integer wishlistID);

    List<Detalle> findByProductoID(Integer productoID);

}

