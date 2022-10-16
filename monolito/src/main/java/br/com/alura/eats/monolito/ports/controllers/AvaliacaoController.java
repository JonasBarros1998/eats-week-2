package br.com.alura.eats.monolito.ports.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.AvaliacaoDto;
import br.com.alura.eats.monolito.application.DTO.MediaAvaliacoesDto;
import br.com.alura.eats.monolito.application.model.Avaliacao;
import br.com.alura.eats.monolito.application.useCase.AvaliacaoUseCase;
import br.com.alura.eats.monolito.ports.repository.AvaliacaoRepository;

@RestController
public class AvaliacaoController {

	@Autowired
	private AvaliacaoRepository repo;

	@GetMapping("/restaurantes/{restauranteId}/avaliacoes")
	List<AvaliacaoDto> listaDoRestaurante(@PathVariable("restauranteId") Long restauranteId) {
		AvaliacaoUseCase avaliacao = new AvaliacaoUseCase(repo);
		return avaliacao.listaDoRestaurante(restauranteId);
		
	}

	@PostMapping("/restaurantes/{restauranteId}/avaliacoes")
	AvaliacaoDto adiciona(@RequestBody Avaliacao avaliacao) {
		AvaliacaoUseCase avaliacaoUseCase = new AvaliacaoUseCase(repo);
		return avaliacaoUseCase.adiciona(avaliacao);
	}

	@GetMapping("/restaurantes/media-avaliacoes")
	List<MediaAvaliacoesDto> mediaDasAvaliacoesDosRestaurantes(@RequestParam("idsDosRestaurantes") List<Long> idsDosRestaurantes) {
		AvaliacaoUseCase avaliacaoUseCase = new AvaliacaoUseCase(repo);
		return avaliacaoUseCase.mediaDasAvaliacoesDosRestaurantes(idsDosRestaurantes);
	}

}
