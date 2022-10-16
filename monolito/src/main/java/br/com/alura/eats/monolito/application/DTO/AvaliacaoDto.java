package br.com.alura.eats.monolito.application.DTO;

import br.com.alura.eats.monolito.application.model.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDto {

	private Long id;
	private Integer nota;
	private String comentario;

	public AvaliacaoDto(Avaliacao avaliacao) {
		this(avaliacao.getId(), avaliacao.getNota(), avaliacao.getComentario());
	}
}
