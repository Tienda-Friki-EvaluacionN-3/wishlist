package com.tiendafriki.lista_deseos.controller;

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

@WebMvcTest(ListaDeseosController.class)
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
}
