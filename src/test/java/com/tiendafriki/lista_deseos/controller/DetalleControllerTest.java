package com.tiendafriki.lista_deseos.controller;

import com.tiendafriki.lista_deseos.dto.DetalleDTO;
import com.tiendafriki.lista_deseos.model.Detalle;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import com.tiendafriki.lista_deseos.service.DetalleServ;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DetalleController.class)
@AutoConfigureMockMvc(addFilters = false)
class DetalleControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetalleServ service;

    
    @Test
    void listarDetalles() throws Exception {

    ListaDeseos wishlist =
            new ListaDeseos(
                    1,
                    "11111111-1",
                    LocalDateTime.now(),
                    null
            );

    Detalle detalle =
            new Detalle(
                    1,
                    wishlist,
                    10,
                    "Producto Test",
                    5000
            );

    when(service.listar())
            .thenReturn(List.of(detalle));

    mockMvc.perform(get("/detalleWishlist/listar"))
            .andExpect(status().isOk());

    }

    @Test
    void buscarDetallePorId() throws Exception {

    ListaDeseos wishlist =
            new ListaDeseos(
                    1,
                    "11111111-1",
                    LocalDateTime.now(),
                    null
            );

    Detalle detalle =
            new Detalle(
                    1,
                    wishlist,
                    10,
                    "Producto Test",
                    5000
            );

    when(service.buscarxID(1))
            .thenReturn(detalle);

    mockMvc.perform(get("/detalleWishlist/buscarxid/1"))
            .andExpect(status().isOk());

    }

    @Test
    void buscarDetallePorWishlist() throws Exception {

    ListaDeseos wishlist =
            new ListaDeseos(
                    1,
                    "11111111-1",
                    LocalDateTime.now(),
                    null
            );

    Detalle detalle =
            new Detalle(
                    1,
                    wishlist,
                    10,
                    "Producto Test",
                    5000
            );

    when(service.buscarxWishlist(1))
            .thenReturn(List.of(detalle));

    mockMvc.perform(get("/detalleWishlist/wishlist/1"))
            .andExpect(status().isOk());

    }

    @Test
    void buscarDetallePorProducto() throws Exception {

    ListaDeseos wishlist =
            new ListaDeseos(
                    1,
                    "11111111-1",
                    LocalDateTime.now(),
                    null
            );

    Detalle detalle =
            new Detalle(
                    1,
                    wishlist,
                    10,
                    "Producto Test",
                    5000
            );

    when(service.buscarxProducto(10))
            .thenReturn(List.of(detalle));

    mockMvc.perform(get("/detalleWishlist/producto/10"))
            .andExpect(status().isOk());

    }

    @Test
    void agregarDetalle() throws Exception {

    when(service.Guardar(any(DetalleDTO.class)))
            .thenReturn("[+] Producto Agregado Correctamente [>_<] ... ");

    mockMvc.perform(post("/detalleWishlist/agregar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                            "wishlistID":1,
                            "productoID":10
                            }
                            """))
            .andExpect(status().isCreated());

    }

    @Test
    void actualizarDetalle() throws Exception {

    when(service.Actualizar(
            org.mockito.ArgumentMatchers.eq(1),
            any(DetalleDTO.class)))
            .thenReturn("[+] Detalle Actualizado Correctamente [>_<] ... ");

    mockMvc.perform(put("/detalleWishlist/actualizar/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                            "wishlistID":1,
                            "productoID":10
                            }
                            """))
            .andExpect(status().isOk());

    }

    @Test
    void eliminarDetalle() throws Exception {

    when(service.Eliminar(1))
            .thenReturn("[+] Detalle Eliminado Correctamente [>_<] ... ");

    mockMvc.perform(delete("/detalleWishlist/eliminar/1"))
            .andExpect(status().isOk());

    }




}
