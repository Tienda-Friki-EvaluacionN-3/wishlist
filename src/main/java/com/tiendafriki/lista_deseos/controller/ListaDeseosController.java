package com.tiendafriki.lista_deseos.controller;

import com.tiendafriki.lista_deseos.dto.ListaDeseosDTO;
import com.tiendafriki.lista_deseos.service.ListaDeseosServ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/wishlist")
public class ListaDeseosController {

    private final ListaDeseosServ s;

    ListaDeseosController(ListaDeseosServ s) {
        this.s = s;
    }

    @Operation(summary = "Listar listas de deseos", description = "Devuelve todas las listas de deseos registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(s.listar());
    }

    @Operation(summary = "Buscar lista de deseos por ID", description = "Busca y devuelve una lista de deseos específica mediante su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Lista de deseos no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/buscarxid/{id}")
    public ResponseEntity<?> buscarxID(@PathVariable Integer id) {
        return ResponseEntity.ok(s.buscarxID(id));
    }

    @Operation(summary = "Buscar lista de deseos por RUT", description = "Busca y devuelve la lista de deseos vinculada al RUT de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Lista de deseos no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/buscarxrut/{rut}")
    public ResponseEntity<?> buscarxRut(@PathVariable String rut) {
        return ResponseEntity.ok(s.buscarxRut(rut));
    }

    @Operation(summary = "Crear lista de deseos", description = "Registra una nueva cabecera de lista de deseos para un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Lista de deseos creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/crear")
    public ResponseEntity<?> Crear(@Valid @RequestBody ListaDeseosDTO dto) {
        return ResponseEntity
                .status(201)
                .body(s.Guardar(dto));
    }

    @Operation(summary = "Eliminar lista de deseos", description = "Elimina permanentemente una lista de deseos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de deseos eliminada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/eliminarxid/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(
                s.Eliminar(id)
        );
    }
}