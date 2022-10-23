package br.com.caelum.eats.pagamento.ports.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/testes")
public class TestesController {
  //@Async
	@PutMapping("/{id}")
	@CircuitBreaker(name = "backendB", fallbackMethod = "processandoFallback")
	public String confirma(@PathVariable("id") Long id) {
		return "backendB";
	}
  
  public String processandoFallback() {
		System.out.println("Processando pagamento");
		return "Retornou aqui";
	}

}
