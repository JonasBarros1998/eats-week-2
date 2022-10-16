package br.com.alura.eats.monolito.application.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.DTO.RestauranteDto;
import br.com.alura.eats.monolito.application.model.Cardapio;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.CardapioRepository;
import br.com.alura.eats.monolito.ports.repository.RestauranteRepository;

public class RestauranteUseCase {

  private RestauranteRepository restauranteRepo;
	private CardapioRepository cardapioRepo;

  public RestauranteUseCase(RestauranteRepository restauranteRepo, CardapioRepository cardapioRepo) {
    this.restauranteRepo = restauranteRepo;
    this.cardapioRepo = cardapioRepo;
  }

	public RestauranteDto detalha(Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	public List<RestauranteDto> detalhePorIds(List<Long> ids) {
		return restauranteRepo.findAllById(ids).stream().map(RestauranteDto::new).collect(Collectors.toList());
	}

	public RestauranteDto detalhaParceiro(Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	public Restaurante adiciona(Restaurante restaurante) {
		restaurante.setAprovado(false);
		Restaurante restauranteSalvo = restauranteRepo.save(restaurante);
		Cardapio cardapio = new Cardapio();
		cardapio.setRestaurante(restauranteSalvo);
		cardapioRepo.save(cardapio);
		return restauranteSalvo;
	}

  public RestauranteDto atualiza(Long id, RestauranteDto restaurante) {
    Restaurante doBD = restauranteRepo.getOne(id);
    restaurante.populaRestaurante(doBD);
    return new RestauranteDto(restauranteRepo.save(doBD));
  }


	public List<RestauranteDto> emAprovacao() {
		return restauranteRepo.findAllByAprovado(false).stream().map(RestauranteDto::new)
				.collect(Collectors.toList());
	}


	public void aprova(Long id) {
		restauranteRepo.aprovaPorId(id);
	}
}
