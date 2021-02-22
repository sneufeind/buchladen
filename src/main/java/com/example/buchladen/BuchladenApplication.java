package com.example.buchladen;

import com.example.buchladen.domain.port.out.BuchSpeichenPort;
import com.example.buchladen.domain.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class BuchladenApplication implements CommandLineRunner {

	@Autowired
	private BuchSpeichenPort buchSpeichenPort;

	public static void main(String[] args) {
		SpringApplication.run(BuchladenApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		log.debug("Stelle Bücher ein...");

		final Autor sebastianFitzek = Autor.namens("Sebastian", "Fitzek");
		final Autor ankeStelling = Autor.namens("Anke", "Stelling");
		final Autor pauloCoelho = Autor.namens("Paulo", "Coelho");

		Arrays.asList(
				new Buch(ISBN.of("978-3-426-19920-6"), "Das Paket", sebastianFitzek, Preis.inEuro(19.99), Genre.THRILLER),
				new Buch(ISBN.of("978-3-426-63792-0"), "Der Seelenbrecher", sebastianFitzek, Preis.inEuro( 9.99), Genre.THRILLER),
				new Buch(ISBN.of("978-3-95732-338-5"), "Schäfchen im Trockenen", ankeStelling, Preis.inEuro(22.), Genre.ROMAN),
				new Buch(ISBN.of("978-3-257-06558-9"), "Der Alchimist", pauloCoelho, Preis.inEuro(50.), Genre.ROMAN)
		).forEach(this.buchSpeichenPort::einstellen);

		log.debug("Alle Bücher eingestellt!");
	}
}
