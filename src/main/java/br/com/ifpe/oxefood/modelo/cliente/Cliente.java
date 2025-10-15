package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//anotaçoes de classe
@Entity
@Table(name = "Cliente")
@SQLRestriction("habilitado = true")


//anotaçoes lombok
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//-----
public class Cliente extends EntidadeAuditavel  {
    
//relação de cliente com usuário
   @OneToOne
   @JoinColumn(nullable = false)
   private Usuario usuario;


   //um cliente pode ter vários endereços
   @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
   @Fetch(FetchMode.SUBSELECT)
   private List<EnderecoCliente> enderecos;

   //campos que será mapeado para uma coluna da tabela cliente
   @Column (nullable = false, length = 100)
   private String nome;

   @Column
   private LocalDate dataNascimento;

   @Column (unique = true)
   private String cpf;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;

}
