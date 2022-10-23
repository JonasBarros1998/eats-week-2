package br.com.alura.eats.monolito.ports.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.eats.monolito.application.model.Usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

  private TokenService tokenService;
  //private UsuarioRepository repository;

  public AutenticacaoViaTokenFilter(TokenService tokenService /*UsuarioRepository repository*/) {
    this.tokenService = tokenService;
    //this.repository = repository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = recuperarToken(request);

    boolean valido = tokenService.isTokenValido(token);
    if (valido) {
      authenticarCliente(token);
    }

    filterChain.doFilter(request, response);
    
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    
    if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }

    return token.substring(7, token.length());
  }

  private void authenticarCliente(String token) {
    String nomeDoUsuario = tokenService.getIdUsuario(token);
    Usuario usuario = new Usuario();
    usuario.setNome(nomeDoUsuario);
    usuario.setSenha("password");

    UsernamePasswordAuthenticationToken authentication = 
      new UsernamePasswordAuthenticationToken(usuario, null, null);

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
