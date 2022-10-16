package br.com.alura.eats.monolito.ports.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.CardapioDto;
import br.com.alura.eats.monolito.application.model.Cardapio;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.CardapioRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CardapioController {

	private CardapioRepository repo;

	@GetMapping("/restaurantes/{idRestaurante}/cardapio")
	CardapioDto cardapioDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		Cardapio cardapio = repo.findByRestaurante(restaurante);
		return new CardapioDto(cardapio);
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}")
	CardapioDto porId(@PathVariable("idCardapio") Long idCardapio) {
		Cardapio cardapio = repo.findById(idCardapio).orElseThrow(() -> new ResourceNotFoundException());
		return new CardapioDto(cardapio);
	}

}
