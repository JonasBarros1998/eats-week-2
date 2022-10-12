package br.com.caelum.eats.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioDeFuncionamentoRepository extends JpaRepository<HorarioDeFuncionamento, Long> {

	List<HorarioDeFuncionamento> findAllByRestaurante(Restaurante restaurante);

}
