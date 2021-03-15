package com.example.buchladen.domain.model;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "namens")
public class Autor {

    @NonNull String vorname;
    @NonNull String nachname;
}
