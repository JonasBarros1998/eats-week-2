package br.com.alura.eats.monolito.application.useCase;

import br.com.alura.eats.monolito.application.DTO.CardapioDto;
import br.com.alura.eats.monolito.application.model.Cardapio;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.CardapioRepository;

public class CardapioUseCase {

  CardapioRepository repo;

	public CardapioUseCase(CardapioRepository repo) {
    this.repo = repo;
  }

	public CardapioDto cardapioDoRestaurante(Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		Cardapio cardapio = repo.findByRestaurante(restaurante);
		return new CardapioDto(cardapio);
	}

	public CardapioDto porId(Long idCardapio) {
		Cardapio cardapio = repo.findById(idCardapio)
      .orElseThrow(() -> new ResourceNotFoundException());
		return new CardapioDto(cardapio);
	}
}
