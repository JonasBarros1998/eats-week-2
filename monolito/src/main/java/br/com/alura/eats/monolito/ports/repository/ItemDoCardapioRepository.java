package br.com.alura.eats.monolito.ports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.eats.monolito.application.model.ItemDoCardapio;

public interface ItemDoCardapioRepository extends JpaRepository<ItemDoCardapio, Long>  {

}
