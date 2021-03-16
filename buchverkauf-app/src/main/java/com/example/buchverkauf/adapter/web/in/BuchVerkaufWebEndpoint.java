package com.example.buchverkauf.adapter.web.in;

import com.example.buchverkauf.adapter.web.in.model.AlleBuecherResponse;
import com.example.buchverkauf.domain.port.BuchKaufenPort;
import com.example.buchverkauf.domain.port.BuecherLadenPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/buecher", produces = MediaType.APPLICATION_JSON_VALUE)
public class BuchVerkaufWebEndpoint {

    private final BuchKaufenPort buchKaufenPort;
    private final BuecherLadenPort buecherLadenPort;

    @Autowired
    BuchVerkaufWebEndpoint(final BuchKaufenPort buchKaufenPort, final BuecherLadenPort buecherLadenPort){
        this.buchKaufenPort = buchKaufenPort;
        this.buecherLadenPort = buecherLadenPort;
    }

    @GetMapping("")
    ResponseEntity<AlleBuecherResponse> buecher() {
        final AlleBuecherResponse response = new AlleBuecherResponse();
        response.setBuecher( this.buecherLadenPort.alleBuecher() );
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/{isbn}/kaufen", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> kaufen(@PathVariable("isbn") final String isbn){
        this.buchKaufenPort.kaufeBuchMit(isbn);
        return ResponseEntity.ok().build();
    }
}
