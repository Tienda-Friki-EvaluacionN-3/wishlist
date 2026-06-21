package com.tiendafriki.lista_deseos.controller;

import com.tiendafriki.lista_deseos.dto.DetalleDTO;
import com.tiendafriki.lista_deseos.service.DetalleServ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/detalleWishlist")
public class DetalleController {

    private final DetalleServ s;

    DetalleController(DetalleServ s) {
        this.s = s;
    }

    @Operation(summary = "Listar detalles de lista de deseos", description = "Devuelve todos los detalles de las listas de deseos guardados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(
                s.listar()
        );
    }

    @Operation(summary = "Buscar detalle por ID", description = "Busca y devuelve un detalle específico de la lista de deseos utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Detalle no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/buscarxid/{id}")
    public ResponseEntity<?> buscarxID(@PathVariable Integer id) {
        return ResponseEntity.ok(
                s.buscarxID(id)
        );
    }

    @Operation(summary = "Buscar detalles por ID de Wishlist", description = "Devuelve la lista de detalles que pertenecen a una lista de deseos específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/wishlist/{wishlistID}")
    public ResponseEntity<?> buscarxWishlist(@PathVariable Integer wishlistID) {
        return ResponseEntity.ok(
                s.buscarxWishlist(wishlistID)
        );
    }

    @Operation(summary = "Buscar detalles por ID de Producto", description = "Devuelve todos los detalles de listas de deseos que contienen un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/producto/{productoID}")
    public ResponseEntity<?> buscarxProducto(@PathVariable Integer productoID) {
        return ResponseEntity.ok(
                s.buscarxProducto(productoID)
        );
    }

    @Operation(summary = "Agregar detalle a la Wishlist", description = "Registra un nuevo producto dentro del detalle de una lista de deseos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Detalle agregado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/agregar")
    public ResponseEntity<?> Agregar(@Valid @RequestBody DetalleDTO dto) {
        return ResponseEntity
                .status(201)
                .body(
                        s.Guardar(dto)
                );
    }

    @Operation(summary = "Actualizar detalle de Wishlist", description = "Modifica los datos de un detalle existente mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> Actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleDTO dto
    ) {
        return ResponseEntity.ok(
                s.Actualizar(id, dto)
        );
    }

    @Operation(summary = "Eliminar detalle de Wishlist", description = "Elimina permanentemente un registro de detalle utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(
                s.Eliminar(id)
        );
    }
}