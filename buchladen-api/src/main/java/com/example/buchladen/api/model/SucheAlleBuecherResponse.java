package com.example.buchladen.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucheAlleBuecherResponse {

    private List<Buch> buecher;
}
