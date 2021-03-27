package com.aps.paulistao.api;

import com.aps.paulistao.api.util.ScraperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class PaulistaoAPIApplication implements CommandLineRunner {

	private final ScraperUtil scraper;

	public static void main(String[] args) {
		SpringApplication.run(PaulistaoAPIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String url = scraper.montarUrlPesquisa("palmeiras", "corinthians", "08/08/2020");
		scraper.getInfoPartida(url);
	}
}