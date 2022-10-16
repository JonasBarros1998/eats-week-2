package br.com.alura.eats.monolito.application.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.DTO.HorarioDeFuncionamentoDto;
import br.com.alura.eats.monolito.application.model.HorarioDeFuncionamento;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.HorarioDeFuncionamentoRepository;

public class HorarioDeFuncionamentoUseCase {

  private HorarioDeFuncionamentoRepository repo;

  public HorarioDeFuncionamentoUseCase(HorarioDeFuncionamentoRepository repo) {
    this.repo = repo;
  }

  
	public HorarioDeFuncionamentoDto detalha(Long id) {
		HorarioDeFuncionamento horario = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
		return new HorarioDeFuncionamentoDto(horario);
	}

	public List<HorarioDeFuncionamentoDto> lista(Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		List<HorarioDeFuncionamento> horariosDoRestaurante = repo.findAllByRestaurante(restaurante);
		return horariosDoRestaurante.stream().map(h -> new HorarioDeFuncionamentoDto(h)).collect(Collectors.toList());
	}

	public HorarioDeFuncionamento adiciona(HorarioDeFuncionamento horarioDeFuncionamento) {
		return repo.save(horarioDeFuncionamento);
	}

	public HorarioDeFuncionamento atualiza(HorarioDeFuncionamento horarioDeFuncionamento) {
		return repo.save(horarioDeFuncionamento);
	}

	public void remove(Long id) {
		repo.deleteById(id);
	}
}
