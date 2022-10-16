package br.com.alura.eats.monolito.application.useCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.DTO.AvaliacaoDto;
import br.com.alura.eats.monolito.application.DTO.MediaAvaliacoesDto;
import br.com.alura.eats.monolito.application.model.Avaliacao;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.ports.repository.AvaliacaoRepository;

public class AvaliacaoUseCase {

  AvaliacaoRepository repo;
  
  public AvaliacaoUseCase(AvaliacaoRepository repo) {
    this.repo = repo;
  }

  public List<AvaliacaoDto> listaDoRestaurante(Long restauranteId) {
    Restaurante restaurante = new Restaurante();
		restaurante.setId(restauranteId);
		return repo.findAllByRestaurante(restaurante)
      .stream()
      .map(a -> new AvaliacaoDto(a))
				.collect(Collectors.toList());
  }

	public AvaliacaoDto adiciona(Avaliacao avaliacao) {
    Avaliacao salvo = repo.save(avaliacao);
		return new AvaliacaoDto(salvo);
  }

	public List<MediaAvaliacoesDto> mediaDasAvaliacoesDosRestaurantes(List<Long> idsDosRestaurantes) {
    List<MediaAvaliacoesDto> medias = new ArrayList<>();
		for (Long restauranteId : idsDosRestaurantes) {
			Double media = repo.mediaDoRestaurantePeloId(restauranteId);
			medias.add(new MediaAvaliacoesDto(restauranteId, media));
		}
		return medias;
  }
}

