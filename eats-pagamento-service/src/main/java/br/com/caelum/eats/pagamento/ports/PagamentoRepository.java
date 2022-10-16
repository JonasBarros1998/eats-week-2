package br.com.caelum.eats.pagamento.ports;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.pagamento.application.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
