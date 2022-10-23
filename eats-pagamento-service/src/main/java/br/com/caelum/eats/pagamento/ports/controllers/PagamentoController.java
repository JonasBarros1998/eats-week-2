package br.com.caelum.eats.pagamento.ports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.caelum.eats.pagamento.application.PagamentoUseCase;
import br.com.caelum.eats.pagamento.application.DTO.PagamentoDto;
import br.com.caelum.eats.pagamento.application.model.Pagamento;
import br.com.caelum.eats.pagamento.ports.PagamentoRepository;
import br.com.caelum.eats.pagamento.ports.PedidoClienteComFeign;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoRepository pagamentoRepo;

	@Autowired
	private PedidoClienteComFeign pedidoCliente;

	@GetMapping
	@Bulkhead(name = "listaPagamentosBulkhead")
	ResponseEntity<List<PagamentoDto>> lista() {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return ResponseEntity.ok(pagamentoUseCase.lista());
	}

	@GetMapping("/{id}")
	public PagamentoDto detalha(@PathVariable("id") Long id) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.detalha(id);
	}

	@PostMapping
	public ResponseEntity<PagamentoDto> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		PagamentoDto criarPagamento = pagamentoUseCase.cria(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(criarPagamento.getId()).toUri();
		return ResponseEntity.created(path).body(criarPagamento);
	}

	 
	@PutMapping("/{id}")
	@CircuitBreaker(name = "confirmaPagamento", fallbackMethod = "processandoFallback")
	@Async
	public PagamentoDto confirma(@PathVariable("id") Long id) throws InterruptedException, ExecutionException {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.confirma(id);
	}
	

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@PathVariable("id") Long id) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.cancela(id);
	}

	
	public PagamentoDto processandoFallback(@PathVariable("id") Long id, Throwable t) {
		System.out.println("Processando pagamento");
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.pagamentoSendoProcessado(id);
	}
	

}