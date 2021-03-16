package com.example.buchempfehlung.adapter.web.in;

import com.example.buchempfehlung.adapter.web.in.model.AlleBuecherResponse;
import com.example.buchempfehlung.domain.port.BuecherLadenPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/buecher", produces = MediaType.APPLICATION_JSON_VALUE)
public class BuchEmpfehlungWebEndpoint {

    private final BuecherLadenPort buecherLadenPort;

    @Autowired
    BuchEmpfehlungWebEndpoint(final BuecherLadenPort buecherLadenPort){
        this.buecherLadenPort = buecherLadenPort;
    }

    @GetMapping("")
    ResponseEntity<AlleBuecherResponse> gibAlleBuecher(){
        final AlleBuecherResponse response = new AlleBuecherResponse();
        response.setBuecher( this.buecherLadenPort.ladeAlleBuecher() );
        return ResponseEntity.ok(response);
    }
}
