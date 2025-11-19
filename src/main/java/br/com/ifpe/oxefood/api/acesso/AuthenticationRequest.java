package br.com.ifpe.oxefood.api.acesso;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//Anotações Lombok
@Data  //Gera automaticamente: getters e setters , toString, equals e hashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotBlank //Validação Jakarta: O campo não pode estar vazio
    private String username;

    @NotBlank
    private String password;
    
}