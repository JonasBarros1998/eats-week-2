package br.com.alura.eats.monolito.ports.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.application.useCase.RestauranteFormaDePagamentoUseCase;
import br.com.alura.eats.monolito.ports.repository.RestauranteFormaDePagamentoRepository;

@RestController
public class RestauranteFormaDePagamentoController {

	@Autowired
	private RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo;

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/formas-de-pagamento")
	void adiciona(@PathVariable("idRestaurante") Long idRestaurante, @RequestBody FormaDePagamento formaDePagamento) {
		RestauranteFormaDePagamentoUseCase restauranteFormaDePagamento = new RestauranteFormaDePagamentoUseCase(restauranteFormaDePagamentoRepo);
		restauranteFormaDePagamento.adiciona(idRestaurante, formaDePagamento);
	}

	@DeleteMapping("/parceiros/restaurantes/{idRestaurante}/formas-de-pagamento/{idFormaDePagamento}")
	void removeDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante, @PathVariable("idFormaDePagamento") Long idFormaDePagamento) {
		RestauranteFormaDePagamentoUseCase restauranteFormaDePagamento = new RestauranteFormaDePagamentoUseCase(restauranteFormaDePagamentoRepo);
		restauranteFormaDePagamento.removeDoRestaurante(idRestaurante, idFormaDePagamento);
	}

	@GetMapping("/restaurantes/{idRestaurante}/formas-de-pagamento")
	List<FormaDePagamento> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		RestauranteFormaDePagamentoUseCase restauranteFormaDePagamento = new RestauranteFormaDePagamentoUseCase(restauranteFormaDePagamentoRepo);
		return restauranteFormaDePagamento.lista(idRestaurante);
	}

}
