package com.example.buchempfehlung;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.example.buchempfehlung.adapter.web.out.BuchladenClient;
import com.example.buchempfehlung.domain.Buch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "buchladen")
@SpringBootTest
public class BuchempfehlungPact {

    private static final String DEFAULT_ACCEPT_HEADER = "application/json, application/*+json";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Pact(consumer = "buchempfehlung")
    public RequestResponsePact ladeBuecher(final PactDslWithProvider builder) {
        // request
        final Map<String, String> requestHeaders = Map.of(
                HttpHeaders.ACCEPT, DEFAULT_ACCEPT_HEADER
        );

        // response
        final Map<String, String> responseHeaders = Map.of(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
        );

        // pact
        final PactDslJsonBody expectedResponseBody = new PactDslJsonBody()
            .eachLike("buecher")
                .stringType("titel")
                .stringType("genre")
                .integerType("verkaufteExemplare")
                .object("autor")
                    .stringType("vorname")
                    .stringType("nachname")
                .closeObject()
            .closeArray()
            .asBody();

        return builder
                .uponReceiving("Lade alle Bücher")
                .method(HttpMethod.GET.name())
                .path("/api/buecher")
                .headers(requestHeaders)
                .willRespondWith()
                .headers(responseHeaders)
                .status(HttpStatus.OK.value())
                .body(expectedResponseBody)
                .toPact();
    }

    @Test
    void ladeBuecher(final MockServer mockServer) {
        // given
        final BuchladenClient client = new BuchladenClient(mockServer.getUrl(), this.restTemplateBuilder);
        // when
        final List<Buch> buecher = client.ladeAlleBuecher();
        // then
        assertThat(buecher).isNotEmpty();
    }
}
