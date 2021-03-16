package com.example.buchverkauf.adapter.web.out.mapper;

import com.example.buchverkauf.domain.model.Buch;

public final class Mapper {

    public static Buch buchToDomain(final com.example.buchladen.api.model.Buch web){
        if(web == null)
            return null;

        final Buch buch = new Buch();
        buch.setIsbn(web.getIsbn());
        buch.setTitel(web.getTitel());
        buch.setPreisInEuro(web.getPreis() == null ? null : web.getPreis().getWert());
        return buch;
    }
}
