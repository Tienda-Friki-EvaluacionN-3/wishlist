package com.tiendafriki.lista_deseos;

import com.tiendafriki.lista_deseos.model.Detalle;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import com.tiendafriki.lista_deseos.repository.DetalleRepo;
import com.tiendafriki.lista_deseos.repository.ListaDeseosRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(
            ListaDeseosRepo wishlistRepo,
            DetalleRepo detalleRepo
    ) {

        return args -> {

            if (wishlistRepo.count() == 0) {

                ListaDeseos w1 = new ListaDeseos(
                        null,
                        "11.111.111-1",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                wishlistRepo.save(w1);

                detalleRepo.save(new Detalle(
                        null,
                        w1,
                        1,
                        "Harry Potter y la piedra filosofal",
                        23000
                ));

                detalleRepo.save(new Detalle(
                        null,
                        w1,
                        3,
                        "Naruto",
                        10000
                ));

                ListaDeseos w2 = new ListaDeseos(
                        null,
                        "22.222.222-2",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                wishlistRepo.save(w2);

                detalleRepo.save(new Detalle(
                        null,
                        w2,
                        4,
                        "One Piece",
                        10000
                ));

                ListaDeseos w3 = new ListaDeseos(
                        null,
                        "33.333.333-3",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                wishlistRepo.save(w3);

                detalleRepo.save(new Detalle(
                        null,
                        w3,
                        2,
                        "Hobbit",
                        43000
                ));

                detalleRepo.save(new Detalle(
                        null,
                        w3,
                        3,
                        "Naruto",
                        10000
                ));

                ListaDeseos w4 = new ListaDeseos(
                        null,
                        "44.444.444-4",
                        LocalDateTime.now(),
                        new ArrayList<>()
                );

                wishlistRepo.save(w4);

                System.out.println(
                        "[+] DataLoader De Wishlist Ejecutado Correctamente [>_<] ..."
                );
            }
        };
    }
}