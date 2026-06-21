package com.tiendafriki.lista_deseos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetalleDTO {

    @NotNull(message = "[+] El ID De La Wishlist No Puede Ser Nulo [>_<] ... ")
    private Integer wishlistID;

    @NotNull(message = "[+] El ID Del Producto No Puede Ser Nulo [>_<] ... ")
    private Integer productoID;
}