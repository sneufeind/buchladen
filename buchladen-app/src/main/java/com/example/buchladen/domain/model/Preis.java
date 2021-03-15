package com.example.buchladen.domain.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class Preis {

    double wert;
    Waehrung waehrung;

    private Preis(final double wert, final Waehrung waehrung){
        this.wert = wert;
        this.waehrung = waehrung;
    }

    public static Preis inEuro(@NonNull final Double wert){
        return new Preis(wert, Waehrung.EURO);
    }

    public static Preis inUSDollar(@NonNull final Double wert){
        return new Preis(wert, Waehrung.US_DOLLAR);
    }
}
