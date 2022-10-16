package br.com.alura.eats.monolito.ports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.CategoriaDoCardapioDto;
import br.com.alura.eats.monolito.application.model.CategoriaDoCardapio;
import br.com.alura.eats.monolito.application.useCase.CategoriaDoCardapioUseCase;
import br.com.alura.eats.monolito.ports.repository.CategoriaDoCardapioRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CategoriaDoCardapioController {

	@Autowired
	private CategoriaDoCardapioRepository repo;

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}")
	CategoriaDoCardapioDto categoriaPorId(@PathVariable("idCategoria") Long idCategoria) {
		CategoriaDoCardapioUseCase categoriaDoCardapio = new CategoriaDoCardapioUseCase(repo);
		return categoriaDoCardapio.categoriaPorId(idCategoria);
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria")
	CategoriaDoCardapioDto cardapioDoRestaurante(@PathVariable("idCardapio") Long idCardapio,
			@RequestBody CategoriaDoCardapio categoria) {
				CategoriaDoCardapioUseCase categoriaDoCardapio = new CategoriaDoCardapioUseCase(repo);
				return categoriaDoCardapio.cardapioDoRestaurante(idCardapio, categoria);
	}

}
