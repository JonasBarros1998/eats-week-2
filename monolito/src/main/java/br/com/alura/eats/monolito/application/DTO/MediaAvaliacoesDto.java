package br.com.alura.eats.monolito.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaAvaliacoesDto {

	private Long restauranteId;
	private Double media;

}
