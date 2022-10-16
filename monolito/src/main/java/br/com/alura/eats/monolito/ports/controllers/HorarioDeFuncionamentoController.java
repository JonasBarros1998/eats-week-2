package br.com.alura.eats.monolito.ports.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.HorarioDeFuncionamentoDto;
import br.com.alura.eats.monolito.application.model.HorarioDeFuncionamento;
import br.com.alura.eats.monolito.application.useCase.HorarioDeFuncionamentoUseCase;
import br.com.alura.eats.monolito.ports.repository.HorarioDeFuncionamentoRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HorarioDeFuncionamentoController {

	@Autowired
	private HorarioDeFuncionamentoRepository repo;

	@GetMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento/{id}")
	public HorarioDeFuncionamentoDto detalha(@PathVariable("id") Long id) {
		HorarioDeFuncionamentoUseCase horarioDeFuncionamentoUseCase = new HorarioDeFuncionamentoUseCase(repo);
		return horarioDeFuncionamentoUseCase.detalha(id);
	}

	@GetMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento")
	public List<HorarioDeFuncionamentoDto> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		HorarioDeFuncionamentoUseCase horarioDeFuncionamentoUseCase = new HorarioDeFuncionamentoUseCase(repo);
		return horarioDeFuncionamentoUseCase.lista(idRestaurante);
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/horarios-de-funcionamento")
	public HorarioDeFuncionamento adiciona(@RequestBody HorarioDeFuncionamento horarioDeFuncionamento) {
		HorarioDeFuncionamentoUseCase horarioDeFuncionamentoUseCase = new HorarioDeFuncionamentoUseCase(repo);
		return horarioDeFuncionamentoUseCase.adiciona(horarioDeFuncionamento);
	}

	@PutMapping("/parceiros/restaurantes/{idRestaurante}/horarios-de-funcionamento/{id}")
	public HorarioDeFuncionamento atualiza(@RequestBody HorarioDeFuncionamento horarioDeFuncionamento) {
		HorarioDeFuncionamentoUseCase horarioDeFuncionamentoUseCase = new HorarioDeFuncionamentoUseCase(repo);
		return horarioDeFuncionamentoUseCase.atualiza(horarioDeFuncionamento);
	}

	@DeleteMapping("/parceiros/restaurantes/{idRestaurante}/horarios-de-funcionamento/{id}")
	public void remove(@PathVariable("id") Long id) {
		HorarioDeFuncionamentoUseCase horarioDeFuncionamentoUseCase = new HorarioDeFuncionamentoUseCase(repo);
		horarioDeFuncionamentoUseCase.remove(id);
	}

}
