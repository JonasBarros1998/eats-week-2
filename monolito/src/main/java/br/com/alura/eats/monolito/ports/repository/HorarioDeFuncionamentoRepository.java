package br.com.alura.eats.monolito.ports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.eats.monolito.application.model.HorarioDeFuncionamento;
import br.com.alura.eats.monolito.application.model.Restaurante;

public interface HorarioDeFuncionamentoRepository extends JpaRepository<HorarioDeFuncionamento, Long> {

	List<HorarioDeFuncionamento> findAllByRestaurante(Restaurante restaurante);

}
