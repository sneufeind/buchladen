package com.example.buchempfehlung.adapter.web.in.model;

import com.example.buchempfehlung.domain.Buch;
import lombok.Data;

import java.util.List;

@Data
public class AlleBuecherResponse {

    private List<Buch> buecher;
}
