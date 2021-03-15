package com.example.buchladen.api;

import com.example.buchladen.api.model.Buch;
import com.example.buchladen.api.model.KaufeBuchRequest;
import com.example.buchladen.api.model.SucheAlleBuecherResponse;
import lombok.NonNull;

public interface BuchladenApi {

    SucheAlleBuecherResponse sucheAlleBuecher();

    Buch findeBuchAnhand(@NonNull String isbn);

    void kaufeBuch(@NonNull String isbn);//, KaufeBuchRequest requestBody);
}
