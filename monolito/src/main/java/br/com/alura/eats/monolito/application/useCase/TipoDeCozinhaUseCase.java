package br.com.alura.eats.monolito.application.useCase;

import br.com.alura.eats.monolito.ports.repository.TipoDeCozinhaRepository;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.DTO.TipoDeCozinhaDto;
import br.com.alura.eats.monolito.application.model.TipoDeCozinha;

public class TipoDeCozinhaUseCase {

  private TipoDeCozinhaRepository repo;
  
  public TipoDeCozinhaUseCase(TipoDeCozinhaRepository repo) {
    this.repo = repo;
  }

	public List<TipoDeCozinhaDto> lista() {
		return repo.findAllByOrderByNomeAsc().stream().map(TipoDeCozinhaDto::new).collect(Collectors.toList());
	}

	public TipoDeCozinhaDto adiciona(TipoDeCozinha tipoDeCozinha) {
		return new TipoDeCozinhaDto(repo.save(tipoDeCozinha));
	}

	public TipoDeCozinhaDto atualiza(TipoDeCozinha tipoDeCozinha) {
		return new TipoDeCozinhaDto(repo.save(tipoDeCozinha));
	}

	public void remove(Long id) {
		repo.deleteById(id);
	}
}
