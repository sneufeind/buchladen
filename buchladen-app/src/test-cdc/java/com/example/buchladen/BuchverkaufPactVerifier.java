package com.example.buchladen;

import au.com.dius.pact.provider.junitsupport.Consumer;
import com.example.buchladen.domain.port.BuchSpeichenPort;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Consumer("buchverkauf")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuchverkaufPactVerifier extends AbstractPactVerifier {

    @Autowired
    private BuchSpeichenPort buchSpeichenPort;

    @BeforeEach
    void setUp(){
        // prepare data
        Arrays.asList(
                Beispiel.Buch.DAS_PAKET,
                Beispiel.Buch.DER_ALCHIMIST,
                Beispiel.Buch.DER_SEELENBRECHER,
                Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN
        ).forEach(this.buchSpeichenPort::einstellen);
    }
}
