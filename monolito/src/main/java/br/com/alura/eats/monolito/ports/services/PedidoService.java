package br.com.alura.eats.monolito.ports.services;

import org.springframework.stereotype.Service;

import br.com.alura.eats.monolito.application.model.Pedido;
import br.com.alura.eats.monolito.application.model.Pedido.Status;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.PedidoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

	private PedidoRepository repo;
	
	public Pedido porIdComItens(Long pedidoId) {
		return repo.porIdComItens(pedidoId).orElseThrow(ResourceNotFoundException::new);
	}

	public void atualizaStatus(Status status, Pedido pedido) {
		repo.atualizaStatus(status, pedido);
	}

}
