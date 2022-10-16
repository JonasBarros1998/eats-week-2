package br.com.alura.eats.monolito.application.useCase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.DTO.FormaDePagamentoDto;
import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.ports.repository.FormaDePagamentoRepository;

public class FormaDePagamentoUseCase {

  private FormaDePagamentoRepository formaRepo;
  
  public FormaDePagamentoUseCase(FormaDePagamentoRepository formaRepo) {
    this.formaRepo = formaRepo;
  }
  
	public List<FormaDePagamentoDto> lista() {
		return formaRepo.findAllByOrderByNomeAsc()
      .stream()
      .map(FormaDePagamentoDto::new)
      .collect(Collectors.toList());
	}

	public List<FormaDePagamento.Tipo> tipos() {
		return Arrays.asList(FormaDePagamento.Tipo.values());
	}

	public FormaDePagamentoDto adiciona(FormaDePagamento tipoDeCozinha) {
		return new FormaDePagamentoDto(formaRepo.save(tipoDeCozinha));
	}

	public FormaDePagamentoDto atualiza(FormaDePagamento tipoDeCozinha) {
		return new FormaDePagamentoDto(formaRepo.save(tipoDeCozinha));
	}

	public void remove(Long id) {
		formaRepo.deleteById(id);
	}

}
