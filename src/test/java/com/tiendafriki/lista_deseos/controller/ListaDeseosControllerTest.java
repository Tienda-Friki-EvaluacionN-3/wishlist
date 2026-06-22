package com.tiendafriki.lista_deseos.controller;

import com.tiendafriki.lista_deseos.dto.ListaDeseosDTO;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import com.tiendafriki.lista_deseos.service.ListaDeseosServ;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.springframework.http.MediaType;

@WebMvcTest(ListaDeseosController.class)
@AutoConfigureMockMvc(addFilters = false)
class ListaDeseosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaDeseosServ service;

    @Test
    void listarListasDeseos() throws Exception {

        ListaDeseos listaDeseos = new ListaDeseos(
                1,
                "11111111-1",
                LocalDateTime.now(),
                null
        );

        List<ListaDeseos> respuesta = List.of(listaDeseos);

        when(service.listar()).thenReturn(respuesta);

        mockMvc.perform(get("/wishlist/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarListaDeseosPorId() throws Exception {

    ListaDeseos listaDeseos = new ListaDeseos(
            1,
            "11111111-1",
            LocalDateTime.now(),
            null
    );

    when(service.buscarxID(1))
            .thenReturn(listaDeseos);

    mockMvc.perform(get("/wishlist/buscarxid/1"))
            .andExpect(status().isOk());


    }

    @Test
    void buscarListaDeseosPorRut() throws Exception {


    ListaDeseos listaDeseos = new ListaDeseos(
            1,
            "11111111-1",
            LocalDateTime.now(),
            null
    );

    when(service.buscarxRut("11111111-1"))
            .thenReturn(listaDeseos);

    mockMvc.perform(get("/wishlist/buscarxrut/11111111-1"))
            .andExpect(status().isOk());


    }

    @Test
    void crearListaDeseos() throws Exception {


    when(service.Guardar(any(ListaDeseosDTO.class)))
            .thenReturn("[+] Wishlist Creada Correctamente [>_<] ... ");

    mockMvc.perform(post("/wishlist/crear")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                    "rut":"11111111-1"
                    }
                    """))
            .andExpect(status().isCreated());


    }

    @Test
    void eliminarListaDeseos() throws Exception {

    when(service.Eliminar(1))
            .thenReturn("[+] Wishlist Eliminada Correctamente [>_<] ... ");

    mockMvc.perform(delete("/wishlist/eliminarxid/1"))
            .andExpect(status().isOk());

    }

}
