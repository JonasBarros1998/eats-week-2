package br.com.alura.eats.monolito.ports.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class TokenService {
  
  @Value("${forum.jwt.expiration}")
  private String expiration;

  @Value("${forum.jwt.secret}")
  private String secret;
  
  public String gerarToken(Authentication authentication) {

    //Usuario logado =(Usuario)authentication.getPrincipal();

    Date hoje = new Date();
    Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

    return Jwts
      .builder()
      .setIssuer("API do forum da alura")
      .setSubject("cliente1")
      .setIssuedAt(hoje)
      .setExpiration(dataExpiracao)
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
  }

  public boolean isTokenValido(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // Recuperar o id do usuario a partir do token forncecido
  public String getIdUsuario(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(this.secret)
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }
}
