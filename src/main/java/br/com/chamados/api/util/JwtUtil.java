package br.com.chamados.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String CHAVE_SECRETA = "0`2t0v8e0<Zx!tF)JV<$d%q.8VcSz#G^4SF#n3#0q{r)EJi3a("; // TODO: usar variável de ambiente
    private static final Key KEY = Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes());
    private static final long TEMPO_EXPERICAO = 1000 * 60 * 60 * 4; // 4 Horas

    public static String gerarToken(String nomeUsuario, Long idUsuario){
        return Jwts.builder()
                .setSubject(nomeUsuario)
                .claim("id", idUsuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPERICAO))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims validarToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean tokenValido(String token){
        try{
            Claims claims = validarToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public  static Long getIdUsuario(String token){
        Claims claims = validarToken(token);
        return claims.get("id", Long.class);
    }

    public static String getNomeUsuario(String token){
        Claims claims = validarToken(token);
        return  claims.getSubject();
    }

}
