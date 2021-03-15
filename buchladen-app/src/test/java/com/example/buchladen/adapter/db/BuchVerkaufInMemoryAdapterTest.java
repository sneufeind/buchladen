package com.example.buchladen.adapter.db;

import com.example.buchladen.Beispiel;
import com.example.buchladen.domain.model.Buch;
import com.example.buchladen.domain.model.ISBN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuchVerkaufInMemoryAdapterTest {

    private BuchVerkaufInMemoryAdapter adapter;

    @BeforeEach
    void setUp() {
        this.adapter = new BuchVerkaufInMemoryAdapter();
    }

    private void verkaufeBuch(final ISBN isbn, final int anzahl){
        for (int i = 1; i <= anzahl; i++) {
            this.adapter.kaufeBuch(isbn);
        }
    }

    @Test
    void kaufeBuch() {
        // given
        final Buch buchA = Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN;
        final Buch buchB = Beispiel.Buch.DAS_PAKET;
        final int verkaufteExemplareA = 5;
        final int verkaufteExemplareB = 3;
        // when
        verkaufeBuch(buchA.getIsbn(), verkaufteExemplareA);
        verkaufeBuch(buchB.getIsbn(), verkaufteExemplareB);
        // then
        assertEquals(verkaufteExemplareA, this.adapter.anzahlVerkaufterExemplare(buchA.getIsbn()));
        assertEquals(verkaufteExemplareB, this.adapter.anzahlVerkaufterExemplare(buchB.getIsbn()));
    }
}