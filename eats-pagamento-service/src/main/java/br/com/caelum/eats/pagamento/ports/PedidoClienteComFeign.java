package br.com.caelum.eats.pagamento.ports;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.caelum.eats.pagamento.application.model.MudancaDeStatusDoPedido;

@FeignClient(name = "monolito")
public interface PedidoClienteComFeign {

    @PutMapping("/pedidos/{idDoPedido}/status")
    void notificaServicoDePedidoParaMudarStatus(@PathVariable("idDoPedido") Long id,
                                                @RequestBody MudancaDeStatusDoPedido body);

}

