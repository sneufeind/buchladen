package com.example.buchempfehlung.adapter.web.out.mapper;

import com.example.buchempfehlung.domain.Buch;

import java.util.*;
import java.util.stream.Collectors;

public class Mapper {

    public static List<Buch> alleBuecherToDomain(final List<com.example.buchladen.api.model.Buch> api) {
        if(api == null) {
            return Collections.emptyList();
        }

        // sortiere buecher nach absteigend nach verkauften Exemplaren
        api.sort( Comparator
                .comparingInt(com.example.buchladen.api.model.Buch::getVerkaufteExemplare)
                .reversed()
        );

        // ermittle Rang-Liste
        final Map<String, Integer> rangListe = new HashMap<>(api.size());
        int rang = 1;
        int verkaeufeVorgaenger = 0;
        int gleicherRangZaehler = 0;
        for (final com.example.buchladen.api.model.Buch buch : api) {
            if (buch.getVerkaufteExemplare() < verkaeufeVorgaenger) {
                rang = rang + gleicherRangZaehler + 1;
                gleicherRangZaehler = 0;
            } else if (buch.getVerkaufteExemplare() == verkaeufeVorgaenger) {
                gleicherRangZaehler++;
            }
            rangListe.put(buch.getIsbn(), rang);
            verkaeufeVorgaenger = buch.getVerkaufteExemplare();
        }

        return api.stream()
                .map(b -> buchToDomain(b, rangListe.get(b.getIsbn())))
                .collect(Collectors.toList());
    }

    private static Buch buchToDomain(final com.example.buchladen.api.model.Buch web, final int rang){
        if(web == null)
            return null;

        final Buch buch = new Buch();
        buch.setTitel(web.getTitle());
        buch.setAutor(autorToDomain(web.getAutor()));
        buch.setGenre(web.getGenre());
        buch.setRang(rang);
        return buch;
    }

    private static String autorToDomain(final com.example.buchladen.api.model.Autor web){
        return web == null ? null : String.format("%s %s", web.getVorname(), web.getNachname());
    }
}
