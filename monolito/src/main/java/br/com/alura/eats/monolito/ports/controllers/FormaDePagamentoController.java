package br.com.alura.eats.monolito.ports.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.FormaDePagamentoDto;
import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.application.useCase.FormaDePagamentoUseCase;
import br.com.alura.eats.monolito.ports.repository.FormaDePagamentoRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FormaDePagamentoController {

	@Autowired
	private FormaDePagamentoRepository formaRepo;

	@GetMapping("/formas-de-pagamento")
	List<FormaDePagamentoDto> lista() {
		FormaDePagamentoUseCase formaDePagamento = new FormaDePagamentoUseCase(this.formaRepo);
		return formaDePagamento.lista();
	}

	@GetMapping("/admin/formas-de-pagamento/tipos")
	List<FormaDePagamento.Tipo> tipos() {
		FormaDePagamentoUseCase formaDePagamento = new FormaDePagamentoUseCase(this.formaRepo);
		return formaDePagamento.tipos();
	}

	@PostMapping("/admin/formas-de-pagamento")
	FormaDePagamentoDto adiciona(@RequestBody FormaDePagamento tipoDeCozinha) {
		FormaDePagamentoUseCase formaDePagamento = new FormaDePagamentoUseCase(this.formaRepo);
		return formaDePagamento.adiciona(tipoDeCozinha);
	}

	@PutMapping("/admin/formas-de-pagamento/{id}")
	FormaDePagamentoDto atualiza(@RequestBody FormaDePagamento tipoDeCozinha) {
		FormaDePagamentoUseCase formaDePagamento = new FormaDePagamentoUseCase(this.formaRepo);
		return formaDePagamento.atualiza(tipoDeCozinha);
	}

	@DeleteMapping("/admin/formas-de-pagamento/{id}")
	void remove(@PathVariable("id") Long id) {
		FormaDePagamentoUseCase formaDePagamento = new FormaDePagamentoUseCase(this.formaRepo);
		formaDePagamento.remove(id);
	}

}
