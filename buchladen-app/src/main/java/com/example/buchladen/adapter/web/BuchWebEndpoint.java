package com.example.buchladen.adapter.web;

import com.example.buchladen.adapter.web.mapper.BuchMapper;
import com.example.buchladen.api.BuchladenApi;
import com.example.buchladen.api.model.Buch;
import com.example.buchladen.api.model.KaufeBuchRequest;
import com.example.buchladen.api.model.SucheAlleBuecherResponse;
import com.example.buchladen.domain.model.ISBN;
import com.example.buchladen.domain.port.BuchLadenPort;
import com.example.buchladen.domain.port.BuchVerkaufenPort;
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
        info = @Info(version = "v1", title = "Buecher API", description = "Schnittstelle zum Abfragen von Buchinformationen"),
        tags = { @Tag(name = "Buecher", description = "Informationen zu Buechern")}
)
@RestController
@RequestMapping(value = "/api/buecher", produces = MediaType.APPLICATION_JSON_VALUE)
public class BuchWebEndpoint implements BuchladenApi {

    private final BuchLadenPort buchLadenPort;
    private final BuchVerkaufenPort buchVerkaufenPort;

    @Autowired
    BuchWebEndpoint(final BuchLadenPort buchLadenPort, final BuchVerkaufenPort buchVerkaufenPort){
        this.buchLadenPort = buchLadenPort;
        this.buchVerkaufenPort = buchVerkaufenPort;
    }

    @Operation(summary = "Liefert eine Liste aller eingestellten Buecher", tags = {"Buecher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buch gefunden",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SucheAlleBuecherResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Es wurde keine gueltlige ISBN uebermittelt",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Das Buch konnte nicht gefunden werden",
                    content = @Content
            )
    })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public SucheAlleBuecherResponse sucheAlleBuecher(){
        final List<Buch> buecher = this.buchLadenPort.sucheAlleBuecher().stream()
                .map(BuchMapper::mapToWeb)
                .collect(Collectors.toList());
        return new SucheAlleBuecherResponse(buecher);
    }

    @Operation(summary = "Liefert das Buch anhand seiner ISBN", tags = {"Buecher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buch gefunden",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Buch.class)
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Es wurde keine gueltlige ISBN uebermittelt",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Das Buch konnte nicht gefunden werden",
                    content = @Content
            )
    })
    @GetMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Buch findeBuchAnhand(
            @Parameter(description = "ISBN des gesuchten Buches")
            @PathVariable("isbn") final String isbn
    ){
        return this.buchLadenPort.findeBuchAnhand(ISBN.withCode(isbn))
                .map(BuchMapper::mapToWeb)
                .orElseThrow(() -> new IllegalArgumentException("Unbekanntes Buch"));
    }

    @Operation(summary = "Registriert den Kauf eines Buches anhand seiner ISBN", tags = {"Buecher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Buch kaufen",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = KaufeBuchRequest.class)
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Es wurde keine gueltlige ISBN uebermittelt",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Das Buch konnte nicht gefunden werden",
                    content = @Content
            )
    })
    @PostMapping(value = "/{isbn}/kaufen", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Override
    public void kaufeBuch(@PathVariable("isbn") final String isbn) {
        this.buchVerkaufenPort.kaufeBuch(ISBN.withCode(isbn));
    }
}
