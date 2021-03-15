package com.example.buchverkauf.domain.model;

import lombok.Data;

@Data
public class Buch {

    private String isbn;
    private String titel;
    private Double preisInEuro;
}
