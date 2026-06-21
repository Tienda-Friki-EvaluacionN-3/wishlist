package com.tiendafriki.lista_deseos;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.tiendafriki.lista_deseos.dto.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ErrorDTO> ErrorValidacion(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(
                        error.getField(),
                        error.getDefaultMessage()
                )
        );

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                400,

                "[+] Error : 400 Error En La Validacion [>_<] ... ",

                errores,

                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)

    public ResponseEntity<ErrorDTO> ErrorSolicitud(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                400,

                "[+] Error : 400 Error En La Solicitud [>_<] ... ",

                null,

                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<ErrorDTO> ErrorNegocio(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        errores.put("error", ex.getMessage());

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                400,

                "[+] Error : 400 Error De Negocio [>_<] ... ",

                errores,

                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)

    public ResponseEntity<ErrorDTO> ErrorNoEncontrado(
            NoSuchElementException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        errores.put("error", ex.getMessage());

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                404,

                "[+] Error : 404 Recurso No Encontrado [>_<] ... ",

                errores,

                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<ErrorDTO> ErrorRuntime(
            RuntimeException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        errores.put("error", ex.getMessage());

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                500,

                "[+] Error : 500 Error Interno Del Servidor [>_<] ... ",

                errores,

                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorDTO> ErrorGeneral(
            Exception ex,
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        errores.put("error", ex.getMessage());

        ErrorDTO error = new ErrorDTO(

                LocalDateTime.now(),

                500,

                "[+] Error : 500 Error Interno Del Servidor [>_<] ... ",

                errores,

                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }


}
