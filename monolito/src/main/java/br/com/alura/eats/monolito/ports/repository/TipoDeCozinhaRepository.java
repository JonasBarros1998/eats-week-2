package br.com.alura.eats.monolito.ports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.eats.monolito.application.model.TipoDeCozinha;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {

	List<TipoDeCozinha> findAllByOrderByNomeAsc();

}
