package br.com.alura.eats.monolito.application.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.eats.monolito.application.model.CategoriaDoCardapio;
import br.com.alura.eats.monolito.application.model.ItemDoCardapio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDoCardapioDto {

	private Long id;
	private String nome;
	private List<ItemDoCardapioDto> itens = new ArrayList<>();

	public CategoriaDoCardapioDto(CategoriaDoCardapio categoria) {
		this(categoria.getId(), categoria.getNome(), trataItens(categoria.getItens()));
	}

	private static List<ItemDoCardapioDto> trataItens(List<ItemDoCardapio> itens) {
		return itens.stream().map(ItemDoCardapioDto::new).collect(Collectors.toList());
	}

}
