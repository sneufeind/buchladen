package com.example.buchladen.domain.model;

import lombok.Data;

@Data
public class Buch {

    private final ISBN isbn;
    private final String titel;
    private final Autor autor;
    private final Preis preis;
    private final Genre genre;
}
