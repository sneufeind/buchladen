package com.example.buchladen.adapter.db;

import com.example.buchladen.domain.model.ISBN;
import com.example.buchladen.domain.port.BuchVerkaufenPort;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BuchVerkaufInMemoryAdapter implements BuchVerkaufenPort {

    private final Map<ISBN, Integer> verkaufteBuecherStore = new HashMap<>();

    @Override
    public void kaufeBuch(@NonNull final ISBN isbn) {
        this.verkaufteBuecherStore.put(isbn, anzahlVerkaufterExemplare(isbn) + 1); // registriere ein weiteres verkauftes Exemplar
    }

    @Override
    public int anzahlVerkaufterExemplare(@NonNull final ISBN isbn) {
        return this.verkaufteBuecherStore.getOrDefault(isbn, 0);
    }
}
