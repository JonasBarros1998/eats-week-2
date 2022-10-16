package br.com.alura.eats.monolito.ports.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.application.model.TipoDeCozinha;
import br.com.alura.eats.monolito.ports.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestauranteService {

	private RestauranteRepository repo;

	public Page<Restaurante> findAllByAprovado(boolean aprovado, Pageable limit) {
		return repo.findAllByAprovado(aprovado, limit);
	}

	public Page<Restaurante> findAllByAprovadoAndTipoDeCozinha(boolean aprovado, TipoDeCozinha tipo, Pageable limit) {
		return repo.findAllByAprovadoAndTipoDeCozinha(aprovado, tipo, limit);
	}

	public Optional<Restaurante> findById(Long id) {
		return repo.findById(id);
	}

}
