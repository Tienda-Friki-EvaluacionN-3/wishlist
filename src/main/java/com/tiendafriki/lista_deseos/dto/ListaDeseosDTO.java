package com.tiendafriki.lista_deseos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ListaDeseosDTO {

    @NotBlank(message = "[+] El Rut No Puede Quedar Vacio [>_<] ... ")

    @Pattern(
            regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$|^\\d{7,8}-[\\dkK]$",
            message = "[+] El Formato Del Rut Es Invalido [>_<] ... "
    )

    private String rut;
}