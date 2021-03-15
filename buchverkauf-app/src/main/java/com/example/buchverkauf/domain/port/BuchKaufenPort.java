package com.example.buchverkauf.domain.port;

import com.example.buchverkauf.domain.model.Buch;
import lombok.NonNull;

public interface BuchKaufenPort {

    void kaufeBuchMit(@NonNull String isbn);
}
