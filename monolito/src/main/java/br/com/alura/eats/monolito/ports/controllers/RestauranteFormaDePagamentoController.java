package br.com.alura.eats.monolito.ports.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.application.model.RestauranteFormaDePagamento;
import br.com.alura.eats.monolito.application.model.RestauranteFormaDePagamento.RestauranteFormaDePagamentoId;
import br.com.alura.eats.monolito.ports.repository.RestauranteFormaDePagamentoRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RestauranteFormaDePagamentoController {

	private RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo;

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/formas-de-pagamento")
	void adiciona(@PathVariable("idRestaurante") Long idRestaurante, @RequestBody FormaDePagamento formaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, formaDePagamento.getId());
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		RestauranteFormaDePagamento restauranteFormaDePagamento = new RestauranteFormaDePagamento(id, restaurante,
				formaDePagamento);
		restauranteFormaDePagamentoRepo.save(restauranteFormaDePagamento);
	}

	@DeleteMapping("/parceiros/restaurantes/{idRestaurante}/formas-de-pagamento/{idFormaDePagamento}")
	void removeDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante, @PathVariable("idFormaDePagamento") Long idFormaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, idFormaDePagamento);
		restauranteFormaDePagamentoRepo.deleteById(id);
	}

	@GetMapping("/restaurantes/{idRestaurante}/formas-de-pagamento")
	List<FormaDePagamento> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		return restauranteFormaDePagamentoRepo.findAllByRestauranteOrderByNomeAsc(restaurante);
	}

}
