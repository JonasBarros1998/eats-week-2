package br.com.caelum.eats.pagamento.ports.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.caelum.eats.pagamento.application.PagamentoUseCase;
import br.com.caelum.eats.pagamento.application.DTO.PagamentoDto;
import br.com.caelum.eats.pagamento.application.model.Pagamento;
import br.com.caelum.eats.pagamento.ports.PagamentoRepository;
import br.com.caelum.eats.pagamento.ports.PedidoClienteComFeign;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
public class PagamentoController {
	
	private PagamentoRepository pagamentoRepo;
	private PedidoClienteComFeign pedidoCliente;

	@GetMapping
	@HystrixCommand(threadPoolKey = "lista")
	ResponseEntity<List<PagamentoDto>> lista() {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return ResponseEntity.ok(pagamentoUseCase.lista());
	}

	@GetMapping("/{id}")
	PagamentoDto detalha(@PathVariable("id") Long id) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.detalha(id);
	}

	@PostMapping
	ResponseEntity<PagamentoDto> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		PagamentoDto criarPagamento = pagamentoUseCase.cria(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(criarPagamento.getId()).toUri();
		return ResponseEntity.created(path).body(criarPagamento);
	}

	@PutMapping("/{id}")
	@HystrixCommand(fallbackMethod = "pagamentoSendoProcessado", threadPoolKey = "confirma")
	@Async
	PagamentoDto confirma(@PathVariable("id") Long id) throws InterruptedException, ExecutionException {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.confirma(id);
	}

	@DeleteMapping("/{id}")
	PagamentoDto cancela(@PathVariable("id") Long id) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.cancela(id);
	}

	PagamentoDto pagamentoSendoProcessado(@PathVariable("id") Long id) {
		PagamentoUseCase pagamentoUseCase = new PagamentoUseCase(pagamentoRepo, pedidoCliente);
		return pagamentoUseCase.pagamentoSendoProcessado(id);

	}

}