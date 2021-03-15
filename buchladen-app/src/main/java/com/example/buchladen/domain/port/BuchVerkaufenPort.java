package com.example.buchladen.domain.port;

import com.example.buchladen.domain.model.ISBN;
import lombok.NonNull;

public interface BuchVerkaufenPort {

    void kaufeBuch(@NonNull ISBN isbn);

    int anzahlVerkaufterExemplare(@NonNull ISBN isbn);
}
