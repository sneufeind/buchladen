package com.example.buchverkauf.adapter.web.out;

import com.example.buchladen.api.model.KaufeBuchRequest;
import com.example.buchladen.api.model.SucheAlleBuecherResponse;
import com.example.buchverkauf.adapter.web.in.mapper.Mapper;
import com.example.buchverkauf.domain.model.Buch;
import com.example.buchverkauf.domain.port.BuchKaufenPort;
import com.example.buchverkauf.domain.port.BuecherLadenPort;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuchladenClient implements BuchKaufenPort, BuecherLadenPort {

    private final RestTemplate httpClient;

    @Autowired
    BuchladenClient(
        @Value("${exmaple.url.buchladen}") final String buchladenUrl,
        final RestTemplateBuilder restTemplateBuilder
    ){
        this.httpClient = restTemplateBuilder
                .rootUri(buchladenUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    @Override
    public void kaufeBuchMit(@NonNull final String isbn) {
        final ResponseEntity<Void> response = this.httpClient.postForEntity(String.format("/api/buecher/%s/kaufen", isbn), null, Void.class);
        if(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError())
            log.warn("{} - {}", response.getStatusCode(), response.toString());
    }

    @Override
    public List<Buch> alleBuecher() {
        final ResponseEntity<SucheAlleBuecherResponse> response = this.httpClient.getForEntity("/api/buecher", SucheAlleBuecherResponse.class);
        return response.getBody() == null ? Collections.emptyList() : response.getBody().getBuecher().stream()
                .map(Mapper::buchToDomain)
                .collect(Collectors.toList());
    }
}
