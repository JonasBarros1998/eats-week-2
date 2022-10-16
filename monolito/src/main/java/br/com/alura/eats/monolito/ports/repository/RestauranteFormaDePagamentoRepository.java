package br.com.alura.eats.monolito.ports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.application.model.Restaurante;
import br.com.alura.eats.monolito.application.model.RestauranteFormaDePagamento;


public interface RestauranteFormaDePagamentoRepository extends JpaRepository<RestauranteFormaDePagamento, RestauranteFormaDePagamento.RestauranteFormaDePagamentoId> {

	@Query("select f from RestauranteFormaDePagamento rf join rf.restaurante r join rf.formaDePagamento f where r = :restaurante order by f.nome")
	List<FormaDePagamento> findAllByRestauranteOrderByNomeAsc(@Param("restaurante") Restaurante restaurante);

}
