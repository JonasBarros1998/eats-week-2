package br.com.alura.eats.monolito.ports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.CardapioDto;
import br.com.alura.eats.monolito.application.useCase.CardapioUseCase;
import br.com.alura.eats.monolito.ports.repository.CardapioRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CardapioController {

	@Autowired
	private CardapioRepository repo;

	@GetMapping("/restaurantes/{idRestaurante}/cardapio")
	CardapioDto cardapioDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante) {
		CardapioUseCase cardapioUseCase = new CardapioUseCase(repo);
		return cardapioUseCase.cardapioDoRestaurante(idRestaurante);
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}")
	CardapioDto porId(@PathVariable("idCardapio") Long idCardapio) {
		CardapioUseCase cardapioUseCase = new CardapioUseCase(repo);
		return cardapioUseCase.porId(idCardapio);
	}

}
