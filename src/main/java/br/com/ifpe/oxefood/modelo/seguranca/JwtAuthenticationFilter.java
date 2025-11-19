package br.com.ifpe.oxefood.modelo.seguranca;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //Torna a classe um componente do Spring.
public class JwtAuthenticationFilter extends OncePerRequestFilter {


   // Injeção das dependências
    private final HandlerExceptionResolver handlerExceptionResolver; //trata erros do token sem quebrar a aplicação.
    private final JwtService jwtService; //extrai usuário do token e valida.
    private final UserDetailsService userDetailsService; //carrega dados do usuário do banco.

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,  //Método principal do filtro
            @NonNull FilterChain filterChain) throws ServletException, IOException {


        //Verifica se existe o header Authorization
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            final String jwt = authHeader.substring(7); //Extrai o token JWT
            final String userEmail = jwtService.extractUsername(jwt); //Extrai o usuário do token

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Verifica se o usuário ainda não está autenticado no contexto

            if (userEmail != null && authentication == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); //Carrega o usuário do banco

                if (jwtService.isTokenValid(jwt, userDetails)) { //Valida o token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( //Coloca o usuário autenticado no SecurityContext
                            userDetails,
                            null,
                            userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}


// O que esse código faz?

// Ele é um filtro de segurança do Spring Security responsável por:

// Ler o token JWT enviado pelo cliente
// Validar o token
// Colocar o usuário autenticado dentro do Spring Security
// Assim, as próximas rotas sabem quem é o usuário e se ele tem permissão.
