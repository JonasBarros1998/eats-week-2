package br.com.caelum.eats.pagamento.ports.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.caelum.eats.pagamento.application.DTO.PagamentoDto;
import br.com.caelum.eats.pagamento.application.model.MudancaDeStatusDoPedido;
import br.com.caelum.eats.pagamento.application.model.Pagamento;
import br.com.caelum.eats.pagamento.ports.PagamentoRepository;
import br.com.caelum.eats.pagamento.ports.PedidoClienteComFeign;
import br.com.caelum.eats.pagamento.ports.ResourceNotFoundException;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;
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
		return ResponseEntity.ok(pagamentoRepo.findAll()
				.stream()
				.map(PagamentoDto::new)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	PagamentoDto detalha(@PathVariable("id") Long id) {
		return pagamentoRepo.findById(id)
				.map(PagamentoDto::new)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@PostMapping
	ResponseEntity<PagamentoDto> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(path).body(new PagamentoDto(salvo));
	}

	@PutMapping("/{id}")
	@HystrixCommand(fallbackMethod = "pagamentoSendoProcessado", threadPoolKey = "confirma")
	@Async
	PagamentoDto confirma(@PathVariable("id") Long id) throws InterruptedException, ExecutionException {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pedidoCliente.notificaServicoDePedidoParaMudarStatus(pagamento.getPedidoId(), new MudancaDeStatusDoPedido("pago"));
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		pagamentoRepo.save(pagamento);

		return CompletableFuture.completedFuture(new PagamentoDto(pagamento)).get();
	}

	@DeleteMapping("/{id}")
	PagamentoDto cancela(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

	PagamentoDto pagamentoSendoProcessado(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.PROCESSANDO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);

	}

}