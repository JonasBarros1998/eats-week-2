package br.com.alura.eats.monolito.ports.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.AvaliacaoDto;
import br.com.alura.eats.monolito.application.DTO.MediaAvaliacoesDto;
import br.com.alura.eats.monolito.application.model.Avaliacao;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.ports.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AvaliacaoController {

	private AvaliacaoRepository repo;

	@GetMapping("/restaurantes/{restauranteId}/avaliacoes")
	List<AvaliacaoDto> listaDoRestaurante(@PathVariable("restauranteId") Long restauranteId) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(restauranteId);
		return repo.findAllByRestaurante(restaurante).stream().map(a -> new AvaliacaoDto(a))
				.collect(Collectors.toList());
	}

	@PostMapping("/restaurantes/{restauranteId}/avaliacoes")
	AvaliacaoDto adiciona(@RequestBody Avaliacao avaliacao) {
		Avaliacao salvo = repo.save(avaliacao);
		return new AvaliacaoDto(salvo);
	}

	@GetMapping("/restaurantes/media-avaliacoes")
	List<MediaAvaliacoesDto> mediaDasAvaliacoesDosRestaurantes(@RequestParam("idsDosRestaurantes") List<Long> idsDosRestaurantes) {
		List<MediaAvaliacoesDto> medias = new ArrayList<>();
		for (Long restauranteId : idsDosRestaurantes) {
			Double media = repo.mediaDoRestaurantePeloId(restauranteId);
			medias.add(new MediaAvaliacoesDto(restauranteId, media));
		}
		return medias;
	}

}
