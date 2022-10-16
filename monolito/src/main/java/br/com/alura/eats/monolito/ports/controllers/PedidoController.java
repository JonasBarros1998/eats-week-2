package br.com.alura.eats.monolito.ports.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.eats.monolito.application.DTO.PedidoDto;
import br.com.alura.eats.monolito.application.model.Pedido;
import br.com.alura.eats.monolito.application.useCase.PedidoUseCase;
import br.com.alura.eats.monolito.ports.repository.PedidoRepository;

@RestController
public class PedidoController {

	@Autowired
	private PedidoRepository repo;

	@GetMapping("/pedidos")
	List<PedidoDto> lista() {
		PedidoUseCase pedido = new PedidoUseCase(repo);
		return pedido.lista();
	}


	@GetMapping("/pedidos/{id}")
	public PedidoDto porId(@PathVariable Long id) {
		PedidoUseCase pedido = new PedidoUseCase(repo);
		return pedido.porId(id);
	}

	@PostMapping("/pedidos")
	PedidoDto adiciona(@RequestBody Pedido pedido) {
		PedidoUseCase pedidoUseCase = new PedidoUseCase(repo);
		return pedidoUseCase.adiciona(pedido);
	}

	@PutMapping("/pedidos/{pedidoId}/status")
	PedidoDto atualizaStatus(@PathVariable Long pedidoId, @RequestBody Pedido pedidoParaAtualizar) throws InterruptedException {
		PedidoUseCase pedido = new PedidoUseCase(repo);
		return pedido.atualizaStatus(pedidoId, pedidoParaAtualizar);
	}

	@PutMapping("/pedidos/{id}/pago")
	void pago(@PathVariable("id") Long id) {
		PedidoUseCase pedido = new PedidoUseCase(repo);
		pedido.pago(id);
		
	}


	@GetMapping("/parceiros/restaurantes/{restauranteId}/pedidos/pendentes")
	List<PedidoDto> pendentes(@PathVariable("restauranteId") Long restauranteId) {
		PedidoUseCase pedido = new PedidoUseCase(repo);
		return pedido.pendentes(restauranteId);
	}

}
