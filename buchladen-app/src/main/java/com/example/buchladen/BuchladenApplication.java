package com.example.buchladen;

import com.example.buchladen.domain.port.BuchSpeichenPort;
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
	public void run(final String... args) {
		log.debug("Stelle Bücher ein...");

		Arrays.asList(
			Beispiel.Buch.DAS_PAKET,
			Beispiel.Buch.DER_ALCHIMIST,
			Beispiel.Buch.DER_SEELENBRECHER,
			Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN
		).forEach(this.buchSpeichenPort::einstellen);

		log.debug("Alle Bücher eingestellt!");
	}
}
