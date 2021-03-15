package com.example.buchladen.adapter.web.mapper;

import com.example.buchladen.domain.model.Autor;
import com.example.buchladen.domain.model.Buch;
import com.example.buchladen.domain.model.Preis;

public class BuchMapper {

    public static com.example.buchladen.api.model.Buch mapToWeb(final Buch buch){
        return buch == null ? null : com.example.buchladen.api.model.Buch.builder()
//                .autor(mapAutorToWeb(buch.getAutor()))
//                .genre(buch.getGenre().name())
                .isbn(buch.getIsbn().getCode())
                .preis(mapPreisToWeb(buch.getPreis()))
                .title(buch.getTitel())
                .build();
    }

    private static String mapAutorToWeb(final Autor autor){
        return autor == null ? null : autor.getVorname() +" "+ autor.getNachname();
    }

    private static com.example.buchladen.api.model.Preis mapPreisToWeb(final Preis preis){
        return preis == null ? null : new com.example.buchladen.api.model.Preis(
                preis.getWert(), preis.getWaehrung().name()
        );
    }
}
