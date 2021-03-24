package com.example.buchladen;

import au.com.dius.pact.provider.junitsupport.Consumer;
import com.example.buchladen.domain.port.BuchSpeichenPort;
import com.example.buchladen.domain.port.BuchVerkaufenPort;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@Consumer("buchempfehlung")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuchempfehlungPactVerifier extends AbstractPactVerifier {

    @Autowired
    private BuchSpeichenPort buchSpeichenPort;
    @Autowired
    private BuchVerkaufenPort buchVerkaufenPort;

    @BeforeEach
    void initData(){
        Stream.of(
                Beispiel.Buch.DAS_PAKET,
                Beispiel.Buch.DER_ALCHIMIST,
                Beispiel.Buch.DER_SEELENBRECHER,
                Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN )
        .peek(this.buchSpeichenPort::einstellen)
        .forEach(buch -> {
            for (int i = 0; i < generateInt(); i++) {
                this.buchVerkaufenPort.anzahlVerkaufterExemplare(buch.getIsbn());
            }
        });
    }

    private static int generateInt(){
        return (int) (Math.random() * 10);
    }
}
