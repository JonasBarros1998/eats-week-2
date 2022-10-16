package br.com.alura.eats.monolito.application.useCase;

import br.com.alura.eats.monolito.application.DTO.CategoriaDoCardapioDto;
import br.com.alura.eats.monolito.application.model.CategoriaDoCardapio;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.CategoriaDoCardapioRepository;

public class CategoriaDoCardapioUseCase {

  private CategoriaDoCardapioRepository repo;

	public CategoriaDoCardapioUseCase(CategoriaDoCardapioRepository repo) {
    this.repo = repo;
  }

  public CategoriaDoCardapioDto categoriaPorId(Long idCategoria) {
		CategoriaDoCardapio categoria = repo.findById(idCategoria).orElseThrow(() -> new ResourceNotFoundException());
		return new CategoriaDoCardapioDto(categoria);
	}

	public CategoriaDoCardapioDto cardapioDoRestaurante(Long idCardapio,
    CategoriaDoCardapio categoria) {

		return new CategoriaDoCardapioDto(repo.save(categoria));
	}
}
