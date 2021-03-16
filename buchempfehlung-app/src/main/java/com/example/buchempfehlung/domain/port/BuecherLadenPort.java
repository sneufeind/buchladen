package com.example.buchempfehlung.domain.port;

import com.example.buchempfehlung.domain.Buch;

import java.util.List;

public interface BuecherLadenPort {

    List<Buch> ladeAlleBuecher();
}
