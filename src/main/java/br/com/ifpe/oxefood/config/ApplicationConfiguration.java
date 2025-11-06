package br.com.ifpe.oxefood.config;
// Importa as classes usadas para configurar o Spring Security e a autenticação
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ifpe.oxefood.modelo.acesso.UsuarioRepository;

@Configuration  // Indica que essa classe define beans de configuração do Spring
public class ApplicationConfiguration {

    
    // Injeção de dependência do repositório de usuários
    private final UsuarioRepository userRepository;
// Construtor que recebe o repositório de usuários
    public ApplicationConfiguration(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }
//Bean responsável por carregar os detalhes de um usuário pelo username
    @Bean
    UserDetailsService userDetailsService() {

   // Retorna uma função que busca o usuário no banco de dados
  // Se não encontrar, lança uma exceção UsernameNotFoundException
        return username -> userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    //Bean responsável por criptografar senhas usando o algoritmo BCrypt
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
 // Recupera o AuthenticationManager padrão configurado pelo Spring
        return config.getAuthenticationManager();
    }

    //Bean que gerencia o processo de autenticação
    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
