package com.example.buchladen.domain.port;

import com.example.buchladen.domain.model.Buch;
import com.example.buchladen.domain.model.ISBN;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface BuchLadenPort {

    List<Buch> sucheAlleBuecher();

    Optional<Buch> findeBuchAnhand(@NonNull ISBN isbn);
}
