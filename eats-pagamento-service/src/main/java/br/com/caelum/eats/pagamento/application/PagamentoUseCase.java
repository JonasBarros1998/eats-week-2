package br.com.caelum.eats.pagamento.application;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import br.com.caelum.eats.pagamento.application.DTO.PagamentoDto;
import br.com.caelum.eats.pagamento.application.model.MudancaDeStatusDoPedido;
import br.com.caelum.eats.pagamento.application.model.Pagamento;
import br.com.caelum.eats.pagamento.ports.PagamentoRepository;
import br.com.caelum.eats.pagamento.ports.PedidoClienteComFeign;
import br.com.caelum.eats.pagamento.ports.ResourceNotFoundException;

public class PagamentoUseCase {
	
	private PagamentoRepository pagamentoRepo;
	private PedidoClienteComFeign pedidoCliente;
  
  public PagamentoUseCase(PagamentoRepository pagamentoRepo, PedidoClienteComFeign pedidoCliente) {
		this.pagamentoRepo = pagamentoRepo;
		this.pedidoCliente = pedidoCliente;
	}

	public List<PagamentoDto> lista() {
		return pagamentoRepo.findAll()
				.stream()
				.map(PagamentoDto::new)
				.collect(Collectors.toList());
	}

	public PagamentoDto detalha(Long id) {
		return pagamentoRepo.findById(id)
				.map(PagamentoDto::new)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public PagamentoDto cria(Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		return new PagamentoDto(salvo);
	}

	public PagamentoDto confirma(Long id) throws InterruptedException, ExecutionException {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pedidoCliente.notificaServicoDePedidoParaMudarStatus(pagamento.getPedidoId(), new MudancaDeStatusDoPedido("pago"));
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		pagamentoRepo.save(pagamento);

		return CompletableFuture.completedFuture(new PagamentoDto(pagamento)).get();
	}

	public PagamentoDto cancela(Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

	public PagamentoDto pagamentoSendoProcessado(Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.PROCESSANDO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);

	}

}
