package com.example.buchladen.adapter.db.impl;

import com.example.buchladen.adapter.db.BuchLadenPort;
import com.example.buchladen.adapter.db.BuchSpeichenPort;
import com.example.buchladen.domain.model.Buch;
import com.example.buchladen.domain.model.ISBN;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuchInMemoryAdapter implements BuchLadenPort, BuchSpeichenPort {

    private final Map<ISBN, Buch> buchStore = new HashMap<>();

    @Override
    public void einstellen(@NonNull final Buch buch) {
        this.buchStore.put(buch.getIsbn(), buch);
    }

    @Override
    public List<Buch> sucheAlleBuecher() {
        return new ArrayList<>(this.buchStore.values());
    }

    @Override
    public Optional<Buch> findeBuchAnhand(@NonNull final ISBN isbn) {
        return Optional.ofNullable(this.buchStore.getOrDefault(isbn, null));
    }
}
