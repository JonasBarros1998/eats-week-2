package br.com.alura.eats.monolito.application.useCase;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.DTO.PedidoDto;
import br.com.alura.eats.monolito.application.model.Pedido;
import br.com.alura.eats.monolito.ports.exceptions.ResourceNotFoundException;
import br.com.alura.eats.monolito.ports.repository.PedidoRepository;

public class PedidoUseCase {
  private PedidoRepository repo;
  
  public PedidoUseCase(PedidoRepository repo) {
    this.repo = repo;
  }

	public List<PedidoDto> lista() {
		return repo.findAll()
				.stream()
				.map(PedidoDto::new)
				.collect(Collectors.toList());
	}


	public PedidoDto porId(Long id) {
		Pedido pedido = repo.findById(id)
      .orElseThrow(ResourceNotFoundException::new);
		return new PedidoDto(pedido);
	}

	public PedidoDto adiciona(Pedido pedido) {
		pedido.setDataHora(LocalDateTime.now());
		pedido.setStatus(Pedido.Status.REALIZADO);
		pedido.getItens().forEach(item -> item.setPedido(pedido));
		pedido.getEntrega().setPedido(pedido);
		Pedido salvo = repo.save(pedido);
		return new PedidoDto(salvo);
	}

	public PedidoDto atualizaStatus(Long pedidoId, Pedido pedidoParaAtualizar) throws InterruptedException {
		System.out.println(">>> " + LocalDateTime.now().getMinute());
		if (LocalDateTime.now().getMinute() % 2 == 0) {
			Pedido pedido = repo.porIdComItens(pedidoId).orElseThrow(ResourceNotFoundException::new);
			pedido.setStatus(pedidoParaAtualizar.getStatus());
			repo.atualizaStatus(pedido.getStatus(), pedido);
			return new PedidoDto(pedido);
		}

		Thread.sleep(300);
		throw new RuntimeException("Não foi possível atualizar o pedido");
	}

	public void pago(Long id) {
		Pedido pedido = repo.porIdComItens(id).orElseThrow(ResourceNotFoundException::new);
		pedido.setStatus(Pedido.Status.PAGO);
		repo.atualizaStatus(Pedido.Status.PAGO, pedido);
	}


	public List<PedidoDto> pendentes(Long restauranteId) {
		return repo.doRestauranteSemOsStatus(restauranteId, Arrays.asList(Pedido.Status.REALIZADO, Pedido.Status.ENTREGUE)).stream()
				.map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
	}
  

}
