package br.com.alura.eats.monolito.application.useCase;

import br.com.alura.eats.monolito.application.DTO.ItemDoCardapioDto;
import br.com.alura.eats.monolito.application.model.ItemDoCardapio;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.ItemDoCardapioRepository;

public class ItemDoCardapioUseCase {
  
  public ItemDoCardapioUseCase(ItemDoCardapioRepository repo) {
    this.repo = repo;
  }

  private ItemDoCardapioRepository repo;

	public ItemDoCardapioDto adicionaItem(ItemDoCardapio item) {
		return new ItemDoCardapioDto(repo.save(item));
	}

	public ItemDoCardapioDto atualizaItem(ItemDoCardapio item) {
		return new ItemDoCardapioDto(repo.save(item));
	}

	public ItemDoCardapioDto itemPorId(Long idItem) {
		ItemDoCardapio item = repo.findById(idItem)
      .orElseThrow(() -> new ResourceNotFoundException());
		return new ItemDoCardapioDto(item);
	}

	public void removeItem(Long idItem) {
		repo.deleteById(idItem);
	}
}
