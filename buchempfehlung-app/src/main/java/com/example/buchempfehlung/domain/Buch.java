package com.example.buchempfehlung.domain;

import lombok.Data;

@Data
public class Buch {

    private String titel;
    private String autor;
    private String genre;
    private int rang;
}
