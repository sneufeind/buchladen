package com.example.buchverkauf.adapter.web.in.model;

import com.example.buchverkauf.domain.model.Buch;
import lombok.Data;

import java.util.List;

@Data
public class AlleBuecherResponse {

    private List<Buch> buecher;
}
