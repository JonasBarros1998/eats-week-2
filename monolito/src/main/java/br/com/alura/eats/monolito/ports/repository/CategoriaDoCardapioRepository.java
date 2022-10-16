package br.com.alura.eats.monolito.ports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.eats.monolito.application.model.CategoriaDoCardapio;

public interface CategoriaDoCardapioRepository extends JpaRepository<CategoriaDoCardapio, Long> {

}
