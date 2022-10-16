package br.com.alura.eats.monolito.ports.controllers;

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

import br.com.alura.eats.monolito.application.DTO.TipoDeCozinhaDto;
import br.com.alura.eats.monolito.application.model.TipoDeCozinha;
import br.com.alura.eats.monolito.application.useCase.TipoDeCozinhaUseCase;
import br.com.alura.eats.monolito.ports.repository.TipoDeCozinhaRepository;

@RestController
public class TipoDeCozinhaController {

	@Autowired
	private TipoDeCozinhaRepository repo;

	@GetMapping("/tipos-de-cozinha")
	List<TipoDeCozinhaDto> lista() {
		TipoDeCozinhaUseCase tipoDeCozinhaUseCase = new TipoDeCozinhaUseCase(repo);
		return tipoDeCozinhaUseCase.lista();
	}

	@PostMapping("/admin/tipos-de-cozinha")
	TipoDeCozinhaDto adiciona(@RequestBody TipoDeCozinha tipoDeCozinha) {
		TipoDeCozinhaUseCase tipoDeCozinhaUseCase = new TipoDeCozinhaUseCase(repo);
		return tipoDeCozinhaUseCase.adiciona(tipoDeCozinha);
	}

	@PutMapping("/admin/tipos-de-cozinha/{id}")
	TipoDeCozinhaDto atualiza(@RequestBody TipoDeCozinha tipoDeCozinha) {
		TipoDeCozinhaUseCase tipoDeCozinhaUseCase = new TipoDeCozinhaUseCase(repo);
		return tipoDeCozinhaUseCase.atualiza(tipoDeCozinha);
	}

	@DeleteMapping("/admin/tipos-de-cozinha/{id}")
	void remove(@PathVariable("id") Long id) {
		TipoDeCozinhaUseCase tipoDeCozinhaUseCase = new TipoDeCozinhaUseCase(repo);
		tipoDeCozinhaUseCase.remove(id);
	}

}
