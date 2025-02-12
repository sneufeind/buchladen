package com.example.buchladen;

import com.example.buchladen.domain.port.BuchSpeichenPort;
import com.example.buchladen.domain.port.BuchVerkaufenPort;
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
	@Autowired
	private BuchVerkaufenPort buchVerkaufenPort;

	public static void main(String[] args) {
		SpringApplication.run(BuchladenApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		if (Arrays.asList(args).contains("--init") ){
			log.debug("Stelle Bücher ein...");

			Arrays.asList(
					Beispiel.Buch.DAS_PAKET,
					Beispiel.Buch.DER_ALCHIMIST,
					Beispiel.Buch.DER_SEELENBRECHER,
					Beispiel.Buch.SCHAEFCHEN_IM_TROCKENEN
			).forEach(buch -> {
				this.buchSpeichenPort.einstellen(buch);
				final int anzahlVerkaufteExemplare = (int) (Math.random() * 10);
				for (int i = 0; i < anzahlVerkaufteExemplare; i++) {
					this.buchVerkaufenPort.kaufeBuch(buch.getIsbn());
				}
				log.info("Anzahl verkaufte Exemplare von Buch '{}': {}", buch.getTitel(), buch.getVerkaufteExemplare());
			});

			log.info("Alle Bücher eingestellt!");
		}
	}
}
