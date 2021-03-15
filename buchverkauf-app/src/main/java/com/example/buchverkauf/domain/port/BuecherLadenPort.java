package com.example.buchverkauf.domain.port;

import com.example.buchverkauf.domain.model.Buch;

import java.util.List;

public interface BuecherLadenPort {

    List<Buch> alleBuecher();
}
