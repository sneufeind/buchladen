package com.example.buchempfehlung.adapter.web.out.mapper;

import com.example.buchladen.api.model.Buch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperTest {

    @Test
    void alleBuecherToDomain_sortedByRang() {
        // given
        final int[] erwarteteRangfolge = { 1,2,2,4 };

        final List<Buch> buecherVonApi = Arrays.asList(
                Buch.builder()
                        .title("Buch A")
                        .isbn("1")
                        .verkaufteExemplare(4)
                        .build(),
                Buch.builder()
                        .title("Buch B")
                        .isbn("2")
                        .verkaufteExemplare(2)
                        .build(),
                Buch.builder()
                        .title("Buch C")
                        .isbn("3")
                        .verkaufteExemplare(7)
                        .build(),
                Buch.builder()
                        .title("Buch D")
                        .isbn("4")
                        .verkaufteExemplare(4)
                        .build()
        );
        // when
        final List<com.example.buchempfehlung.domain.Buch> buecher = Mapper.alleBuecherToDomain(buecherVonApi);
        // then
        for (int i = 0; i < erwarteteRangfolge.length; i++) {
            assertEquals(erwarteteRangfolge[i], buecher.get(i).getRang());
        }
    }
}