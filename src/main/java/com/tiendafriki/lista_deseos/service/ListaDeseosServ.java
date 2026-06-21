package com.tiendafriki.lista_deseos.service;

import com.tiendafriki.lista_deseos.dto.ListaDeseosDTO;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import com.tiendafriki.lista_deseos.repository.ListaDeseosRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.*;

@Service

public class ListaDeseosServ {

    private final ListaDeseosRepo ldr;

    ListaDeseosServ(ListaDeseosRepo ldr) {
        this.ldr = ldr;
    }

    public List<ListaDeseos> listar() {

        return ldr.findAll();
    }

    public ListaDeseos buscarxID(Integer id) {

        return ldr.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Wishlist No Encontrada [>_<] ... "
                        )
                );
    }

    public ListaDeseos buscarxRut(String rut) {

        return ldr.findByRut(rut)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "[+] Wishlist No Encontrada [>_<] ... "
                        )
                );
    }

    public String Guardar(ListaDeseosDTO dto) {

        if (ldr.findByRut(dto.getRut()).isPresent()) {

        throw new IllegalArgumentException(
                "[+] Ya Existe Una Wishlist Para Este Usuario [>_<] ... "
        );
        }

        RestTemplate restTemplate = new RestTemplate();

        String url =
                "http://localhost:8080/auth/buscarxrutusuario/"
                        + dto.getRut();

        try {

        Object usuario =
                restTemplate.getForObject(url, Object.class);

        if (usuario == null) {

                throw new NoSuchElementException(
                        "[+] El Usuario No Existe [>_<] ... "
                );
        }

        }

        catch (HttpClientErrorException.NotFound e) {

        throw new NoSuchElementException(
                "[+] El Usuario No Existe [>_<] ... "
        );
        }

        catch (Exception e) {

        throw new RuntimeException(
                "[+] Error Al Conectarse Con Usuarios [>_<] ... "
        );
        }

        ListaDeseos ld = new ListaDeseos();

        ld.setRut(dto.getRut());

        ld.setFecha(LocalDateTime.now());

        ldr.save(ld);

        return "[+] Wishlist Creada Correctamente [>_<] ... ";


        }

    public String Eliminar(Integer id) {

        ListaDeseos ld = buscarxID(id);

        ldr.delete(ld);

        return "[+] Wishlist Eliminada Correctamente [>_<] ... ";
    }
}