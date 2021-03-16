package com.example.buchladen.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buch {

    private String titel;
    private String isbn;
    private Autor autor;
    private String genre;
    private Preis preis;
    private int verkaufteExemplare;
}
