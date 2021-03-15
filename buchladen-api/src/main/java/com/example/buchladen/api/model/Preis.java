package com.example.buchladen.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preis {

    private Double wert;
    private String waehrung;

    public static Preis inEuro(@NonNull final Double wert){
        return new Preis(wert, "€");
    }
}
