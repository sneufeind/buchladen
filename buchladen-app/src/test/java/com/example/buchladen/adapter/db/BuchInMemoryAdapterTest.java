package com.example.buchladen.adapter.db;

import com.example.buchladen.Beispiel;
import com.example.buchladen.domain.model.Buch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BuchInMemoryAdapterTest {

    private BuchInMemoryAdapter adapter;

    @BeforeEach
    void setUp() {
        this.adapter = new BuchInMemoryAdapter();
    }

    @Test
    void sucheAlleBuecher() {
        // given
        final List<Buch> erwarteteBuecher = Arrays.asList(
                Beispiel.Buch.DAS_PAKET,
                Beispiel.Buch.DER_SEELENBRECHER,
                Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN
        );
        erwarteteBuecher.forEach(this.adapter::einstellen);
        // when
        final List<Buch> buecher = this.adapter.sucheAlleBuecher();
        // then
        assertEquals(erwarteteBuecher.size(), buecher.size());
    }

    @Test
    void findeBuchAnhand() {
        // given
        final Buch buch = Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN;
        this.adapter.einstellen(buch);
        // when
        final Optional<Buch> buchOpt = this.adapter.findeBuchAnhand(buch.getIsbn());
        // then
        assertTrue(buchOpt.isPresent());
    }
}