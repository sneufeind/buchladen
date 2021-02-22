package com.example.buchladen.domain.port.out;

import com.example.buchladen.domain.model.Buch;
import lombok.NonNull;

public interface BuchSpeichenPort {

    void einstellen(@NonNull Buch buch);
}
