package br.com.alura.eats.monolito.application.DTO;

import br.com.alura.eats.monolito.application.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

	private String nome;
	private String cpf;
	private String email;
	private String telefone;

	ClienteDto(Cliente cliente) {
		this(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone());
	}

}
