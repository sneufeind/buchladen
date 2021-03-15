package com.example.buchladen;

import com.example.buchladen.domain.model.*;

public final class Beispiel {

    public static final class Autor {
        public static final com.example.buchladen.domain.model.Autor SEBASTIAN_FITZEK
                = com.example.buchladen.domain.model.Autor.namens("Sebastian", "Fitzek");
        public static final com.example.buchladen.domain.model.Autor ANKE_STELLING
                = com.example.buchladen.domain.model.Autor.namens("Anke", "Stelling");
        public static final com.example.buchladen.domain.model.Autor PAULO_COELHO
                = com.example.buchladen.domain.model.Autor.namens("Paulo", "Coelho");
    }

    public static final class Buch {
        public static final com.example.buchladen.domain.model.Buch DAS_PAKET
                = new com.example.buchladen.domain.model.Buch(ISBN.withCode("978-3-426-19920-6"), "Das Paket", Autor.SEBASTIAN_FITZEK, Preis.inEuro(19.99), Genre.THRILLER);
        public static final com.example.buchladen.domain.model.Buch DER_SEELENBRECHER
                = new com.example.buchladen.domain.model.Buch(ISBN.withCode("978-3-426-63792-0"), "Der Seelenbrecher", Autor.SEBASTIAN_FITZEK, Preis.inEuro( 9.99), Genre.THRILLER);
        public static final com.example.buchladen.domain.model.Buch SCHAEFCHEN_IM_TROCKENEN
                = new com.example.buchladen.domain.model.Buch(ISBN.withCode("978-3-95732-338-5"), "Schäfchen im Trockenen", Autor.ANKE_STELLING, Preis.inEuro(22.), Genre.ROMAN);
        public static final com.example.buchladen.domain.model.Buch DER_ALCHIMIST
                = new com.example.buchladen.domain.model.Buch(ISBN.withCode("978-3-257-06558-9"), "Der Alchimist", Autor.PAULO_COELHO, Preis.inEuro(50.), Genre.ROMAN);
    }

}
