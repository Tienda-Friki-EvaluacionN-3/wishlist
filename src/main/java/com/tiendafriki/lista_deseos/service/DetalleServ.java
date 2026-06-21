package com.tiendafriki.lista_deseos.service;

import com.tiendafriki.lista_deseos.dto.DetalleDTO;
import com.tiendafriki.lista_deseos.model.Detalle;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import com.tiendafriki.lista_deseos.repository.DetalleRepo;
import com.tiendafriki.lista_deseos.repository.ListaDeseosRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service

public class DetalleServ {

    private final DetalleRepo dr;

    private final ListaDeseosRepo wr;

    DetalleServ(ListaDeseosRepo wr, DetalleRepo dr) {
        this.wr = wr;
        this.dr = dr;
    }

    public List<Detalle> listar() {

        return dr.findAll();
    }

    public Detalle buscarxID(Integer id) {

        return dr.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Detalle No Encontrado [>_<] ... "
                        )
                );
    }

    public List<Detalle> buscarxWishlist(Integer wishlistID) {

        return dr.findByWishlist_Id(wishlistID);
    }

    public List<Detalle> buscarxProducto(Integer productoID) {

        return dr.findByProductoID(productoID);
    }

    public String Guardar(DetalleDTO dto) {

        ListaDeseos wt = wr.findById(dto.getWishlistID())
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Wishlist No Existe [>_<] ... "
                        )
                );

        List<Detalle> existente =
                dr.findByWishlist_Id(dto.getWishlistID());

        boolean yaAgregado = existente.stream()
                .anyMatch(d ->
                        d.getProductoID()
                                .equals(dto.getProductoID())
                );

        if (yaAgregado) {

        throw new IllegalArgumentException(
                "[+] El Producto Ya Existe En La Wishlist [>_<] ... "
        );
        }

        RestTemplate restTemplate = new RestTemplate();

        String url =
                "http://localhost:8081/catalogo/buscarxid/"
                        + dto.getProductoID();

        try {

        Map<String, Object> producto =
                restTemplate.getForObject(url, Map.class);

        if (producto == null || producto.isEmpty()) {

                throw new NoSuchElementException(
                        "[+] Producto No Existe En Catalogo [>_<] ... "
                );
        }

        String nombreProducto =
                producto.get("titulo").toString();

        Integer precioProducto =
                Integer.valueOf(
                        producto.get("precio").toString()
                );

        Detalle detalle = new Detalle();

        detalle.setWishlist(wt);

        detalle.setProductoID(dto.getProductoID());

        detalle.setProducto(nombreProducto);

        detalle.setPrecio(precioProducto);

        dr.save(detalle);

        return "[+] Producto Agregado Correctamente [>_<] ... ";

        }

        catch (HttpClientErrorException.NotFound e) {

        throw new NoSuchElementException(
                "[+] Producto No Existe En Catalogo [>_<] ... "
        );
        }

        catch (Exception e) {

        throw new RuntimeException(
                "[+] Error Al Conectarse Con Catalogo [>_<] ... "
        );
        }

        }

    public String Actualizar(Integer id, DetalleDTO dto) {

        Detalle detalle = dr.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Detalle No Encontrado [>_<] ... "
                        )
                );

        RestTemplate restTemplate = new RestTemplate();

        String url =
                "http://localhost:8081/catalogo/buscarxid/"
                        + dto.getProductoID();

        try {

        Map<String, Object> producto =
                restTemplate.getForObject(url, Map.class);

        if (producto == null || producto.isEmpty()) {

                throw new NoSuchElementException(
                        "[+] Producto No Existe [>_<] ... "
                );
        }

        String nombreProducto =
                producto.get("titulo").toString();

        Integer precioProducto =
                Integer.valueOf(
                        producto.get("precio").toString()
                );

        detalle.setProductoID(dto.getProductoID());

        detalle.setProducto(nombreProducto);

        detalle.setPrecio(precioProducto);

        dr.save(detalle);

        return "[+] Detalle Actualizado Correctamente [>_<] ... ";

        }

        catch (HttpClientErrorException.NotFound e) {

        throw new NoSuchElementException(
                "[+] Producto No Existe En Catalogo [>_<] ... "
        );
        }

        catch (Exception e) {

        throw new RuntimeException(
                "[+] Error Al Conectarse Con Catalogo [>_<] ... "
        );
        }

        }

    public String Eliminar(Integer id) {

        Detalle detalle = dr.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Detalle No Encontrado [>_<] ... "
                        )
                );

        dr.delete(detalle);

        return "[+] Detalle Eliminado Correctamente [>_<] ... ";
    }
}