package br.com.alura.eats.monolito.ports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.ItemDoCardapioDto;
import br.com.alura.eats.monolito.application.model.ItemDoCardapio;
import br.com.alura.eats.monolito.application.useCase.ItemDoCardapioUseCase;
import br.com.alura.eats.monolito.ports.repository.ItemDoCardapioRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ItemDoCardapioController {

	@Autowired
	private ItemDoCardapioRepository repo;

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item")
	public ItemDoCardapioDto adicionaItem(@RequestBody ItemDoCardapio item) {
		ItemDoCardapioUseCase itemCardapio = new ItemDoCardapioUseCase(repo);
		return itemCardapio.adicionaItem(item);
	}

	@PutMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public ItemDoCardapioDto atualizaItem(@RequestBody ItemDoCardapio item) {
		ItemDoCardapioUseCase itemCardapio = new ItemDoCardapioUseCase(repo);
		return itemCardapio.adicionaItem(item);
	}

	@GetMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public ItemDoCardapioDto itemPorId(@PathVariable("idItem") Long idItem) {
		ItemDoCardapioUseCase itemCardapio = new ItemDoCardapioUseCase(repo);
		return itemCardapio.itemPorId(idItem);
	}

	@DeleteMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public void removeItem(@PathVariable("idItem") Long idItem) {
		ItemDoCardapioUseCase itemCardapio = new ItemDoCardapioUseCase(repo);
		itemCardapio.removeItem(idItem);
	}

}
