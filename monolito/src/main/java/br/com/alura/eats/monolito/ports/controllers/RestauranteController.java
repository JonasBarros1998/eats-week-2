package br.com.alura.eats.monolito.ports.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.RestauranteDto;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.application.useCase.RestauranteUseCase;
import br.com.alura.eats.monolito.ports.repository.CardapioRepository;
import br.com.alura.eats.monolito.ports.repository.RestauranteRepository;

@RestController
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepo;

	@Autowired
	private CardapioRepository cardapioRepo;

	@GetMapping("/restaurantes/{id}")
	RestauranteDto detalha(@PathVariable("id") Long id) {
		RestauranteUseCase restauranteUseCase = new RestauranteUseCase(restauranteRepo, cardapioRepo);
		
		return restauranteUseCase.detalha(id);
	}

	@GetMapping("/restaurantes")
	List<RestauranteDto> detalhePorIds(@RequestParam("ids") List<Long> ids) {
		RestauranteUseCase restauranteUseCase = new RestauranteUseCase(restauranteRepo, cardapioRepo);
		return restauranteUseCase.detalhePorIds(ids);
	}

	@GetMapping("/parceiros/restaurantes/{id}")
	RestauranteDto detalhaParceiro(@PathVariable("id") Long id) {
		RestauranteUseCase restauranteUseCase = new RestauranteUseCase(restauranteRepo, cardapioRepo);
		return restauranteUseCase.detalhaParceiro(id);
	}

	@PostMapping("/parceiros/restaurantes")
	Restaurante adiciona(@RequestBody Restaurante restaurante) {
		RestauranteUseCase restauranteUseCase = new RestauranteUseCase(restauranteRepo, cardapioRepo);
		return restauranteUseCase.adiciona(restaurante);
	}

  @PutMapping("/parceiros/restaurantes/{id}")
  public RestauranteDto atualiza(@PathVariable Long id, @RequestBody RestauranteDto restaurante) {
		RestauranteUseCase restauranteUseCase = new RestauranteUseCase(restauranteRepo, cardapioRepo);
    return restauranteUseCase.atualiza(id, restaurante);
  }


  @GetMapping("/admin/restaurantes/em-aprovacao")
	List<RestauranteDto> emAprovacao() {
		return restauranteRepo.findAllByAprovado(false).stream().map(RestauranteDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	@PatchMapping("/admin/restaurantes/{id}")
	public void aprova(@PathVariable("id") Long id) {
		restauranteRepo.aprovaPorId(id);
	}
}
