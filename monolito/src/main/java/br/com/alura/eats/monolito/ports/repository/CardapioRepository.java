package br.com.alura.eats.monolito.ports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.eats.monolito.application.model.Cardapio;
import br.com.alura.eats.monolito.application.model.Restaurante;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

	Cardapio findByRestaurante(Restaurante restaurante);
}
