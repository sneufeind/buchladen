package com.example.buchempfehlung.adapter.web.out;

import com.example.buchempfehlung.adapter.web.out.mapper.Mapper;
import com.example.buchempfehlung.domain.Buch;
import com.example.buchempfehlung.domain.port.BuecherLadenPort;
import com.example.buchladen.api.model.SucheAlleBuecherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class BuchladenClient implements BuecherLadenPort {

    private final RestTemplate httpClient;

    @Autowired
    BuchladenClient(
            @Value("${exmaple.url.buchladen}") final String buchladenUrl,
            final RestTemplateBuilder restTemplateBuilder
    ){
        this.httpClient = restTemplateBuilder
                .rootUri(buchladenUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<Buch> ladeAlleBuecher() {
        final ResponseEntity<SucheAlleBuecherResponse> response = this.httpClient.getForEntity("/api/buecher", SucheAlleBuecherResponse.class);
        return response.getBody() == null ? Collections.emptyList()
                : Mapper.alleBuecherToDomain(response.getBody().getBuecher());
    }
}
