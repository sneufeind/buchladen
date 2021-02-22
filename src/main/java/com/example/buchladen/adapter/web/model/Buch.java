package com.example.buchladen.adapter.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buch {

    private String title;
    private String isbn;
    private String genre;
    private Preis preis;
    private String autor;
}
