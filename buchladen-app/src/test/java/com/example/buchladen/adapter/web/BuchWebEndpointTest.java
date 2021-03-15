package com.example.buchladen.adapter.web;

import com.example.buchladen.Beispiel;
import com.example.buchladen.adapter.db.BuchInMemoryAdapter;
import com.example.buchladen.adapter.db.BuchVerkaufInMemoryAdapter;
import com.example.buchladen.api.model.KaufeBuchRequest;
import com.example.buchladen.api.model.SucheAlleBuecherResponse;
import com.example.buchladen.domain.model.Buch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuchWebEndpointTest {

    private BuchInMemoryAdapter buchAdapter;
    private BuchVerkaufInMemoryAdapter buchVerkaufAdapter;
    private BuchWebEndpoint endpoint;

    @BeforeEach
    void setUp() {
        this.buchAdapter = new BuchInMemoryAdapter();
        this.buchVerkaufAdapter = new BuchVerkaufInMemoryAdapter();
        this.endpoint = new BuchWebEndpoint(this.buchAdapter, this.buchVerkaufAdapter);
    }

    @Test
    void sucheAlleBuecher() {
        // given
        final List<Buch> buecher = Arrays.asList(
                Beispiel.Buch.DAS_PAKET,
                Beispiel.Buch.DER_SEELENBRECHER,
                Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN
        );
        buecher.forEach(this.buchAdapter::einstellen);
        // when
        final SucheAlleBuecherResponse response = this.endpoint.sucheAlleBuecher();
        // then
        assertEquals(buecher.size(), response.getBuecher().size());
    }

    @Test
    void findeBuchAnhand() {
        // given
        final Buch erwartetesBuch = Beispiel.Buch.DER_ALCHIMIST;
        this.buchAdapter.einstellen(erwartetesBuch);
        // when
        final com.example.buchladen.api.model.Buch buch = this.endpoint.findeBuchAnhand(erwartetesBuch.getIsbn().getCode());
        // then
        assertEquals(erwartetesBuch.getIsbn().getCode(), buch.getIsbn());
        assertEquals(erwartetesBuch.getTitel(), buch.getTitle());
    }

    @Test
    void kaufeBuch() {
        // given
        final Buch buch = Beispiel.Buch.DER_SEELENBRECHER;
        this.buchAdapter.einstellen(buch);
        assertEquals(0, this.buchVerkaufAdapter.anzahlVerkaufterExemplare(buch.getIsbn()));
        // when
        this.endpoint.kaufeBuch(buch.getIsbn().getCode());//, new KaufeBuchRequest());
        this.endpoint.kaufeBuch(buch.getIsbn().getCode());//, new KaufeBuchRequest());
        // then
        assertEquals(2, this.buchVerkaufAdapter.anzahlVerkaufterExemplare(buch.getIsbn()));
    }
}