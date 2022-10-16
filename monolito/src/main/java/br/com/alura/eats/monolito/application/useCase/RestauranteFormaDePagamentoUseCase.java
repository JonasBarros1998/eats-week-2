package br.com.alura.eats.monolito.application.useCase;

import java.util.List;

import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.application.model.RestauranteFormaDePagamento;
import br.com.alura.eats.monolito.application.model.RestauranteFormaDePagamento.RestauranteFormaDePagamentoId;
import br.com.alura.eats.monolito.ports.repository.RestauranteFormaDePagamentoRepository;

public class RestauranteFormaDePagamentoUseCase {
  
  private RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo;
  
  public RestauranteFormaDePagamentoUseCase(RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo) {
    this.restauranteFormaDePagamentoRepo = restauranteFormaDePagamentoRepo;
  }

	public void adiciona(Long idRestaurante, FormaDePagamento formaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, formaDePagamento.getId());
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		RestauranteFormaDePagamento restauranteFormaDePagamento = new RestauranteFormaDePagamento(id, restaurante,
				formaDePagamento);
		restauranteFormaDePagamentoRepo.save(restauranteFormaDePagamento);
	}

	public void removeDoRestaurante(Long idRestaurante, Long idFormaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, idFormaDePagamento);
		restauranteFormaDePagamentoRepo.deleteById(id);
	}

	public List<FormaDePagamento> lista(Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		return restauranteFormaDePagamentoRepo.findAllByRestauranteOrderByNomeAsc(restaurante);
	}
}
