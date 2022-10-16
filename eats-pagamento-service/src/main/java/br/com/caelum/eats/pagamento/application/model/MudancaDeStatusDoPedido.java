package br.com.caelum.eats.pagamento.application.model;

public class MudancaDeStatusDoPedido {
    private String status;

    public MudancaDeStatusDoPedido(String status) {
        this.status = status.toUpperCase();
    }

    public String getStatus() {
        return status;
    }
}
