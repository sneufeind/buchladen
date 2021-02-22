package com.example.buchladen.adapter.web;

import com.example.buchladen.domain.port.out.BuchLadenPort;
import com.example.buchladen.adapter.web.mapper.BuchMapper;
import com.example.buchladen.adapter.web.model.Buch;
import com.example.buchladen.adapter.web.model.SucheAlleBuecherResponse;
import com.example.buchladen.domain.model.ISBN;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@OpenAPIDefinition(
        info = @Info(version = "v1", title = "Bücher API", description = "Schnittstelle zum Abfragen von Buchinformationen"),
        tags = { @Tag(name = "Bücher", description = "Informationen zu Büchern")}
)
@RestController
@RequestMapping(value = "/api/v1/buecher", produces = MediaType.APPLICATION_JSON_VALUE)
public class BuchWebEndpointV1 {

    private final BuchLadenPort buchLadenPort;

    @Autowired
    BuchWebEndpointV1(final BuchLadenPort buchLadenPort){
        this.buchLadenPort = buchLadenPort;
    }

    @Operation(summary = "Liefert eine Liste aller eingestellten Bücher", tags = {"Bücher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buch gefunden",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SucheAlleBuecherResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Es wurde keine gültlige ISBN übermittelt",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Das Buch konnte nicht gefunden werden",
                    content = @Content
            )
    })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    SucheAlleBuecherResponse buecher(){
        final List<Buch> buecher = this.buchLadenPort.sucheAlleBuecher().stream()
                .map(BuchMapper::mapToWeb)
                .collect(Collectors.toList());
        return new SucheAlleBuecherResponse(buecher);
    }

    @Operation(summary = "Liefert das Buch anhand seiner ISBN", tags = {"Bücher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buch gefunden",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Buch.class)
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Es wurde keine gültlige ISBN übermittelt",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Das Buch konnte nicht gefunden werden",
                    content = @Content
            )
    })
    @GetMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    Buch getBuchByISBN(
            @Parameter(description = "ISBN des gesuchten Buches")
            @PathVariable("isbn") final String isbn
    ){
        return this.buchLadenPort.findeBuchAnhand(ISBN.of(isbn))
                .map(BuchMapper::mapToWeb)
                .orElseThrow(() -> new IllegalArgumentException("Unbekanntes Buch"));
    }
}
