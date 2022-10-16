package br.com.alura.eats.monolito.application.DTO;

import br.com.alura.eats.monolito.application.model.FormaDePagamento;
import br.com.alura.eats.monolito.application.model.FormaDePagamento.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaDePagamentoDto {

	private Long id;
	private Tipo tipo;
	private String nome;

	public FormaDePagamentoDto(FormaDePagamento forma) {
		this(forma.getId(), forma.getTipo(), forma.getNome());
	}
	
}
