package br.com.ifpe.oxefood.util.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

//Anotações principais da classe
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntidadeAuditavel extends EntidadeNegocio {
  
   @JsonIgnore
   @Version
   private Long versao;


//    @CreatedDate → preenchido automaticamente com a data de criação do registro.
// @JsonIgnore → não será enviado no JSON da API.

   @JsonIgnore
   @CreatedDate
   private LocalDate dataCriacao;

   @JsonIgnore
   @LastModifiedDate //3️ Data da última modificação
   private LocalDate dataUltimaModificacao;

    @CreatedBy //o Spring preenche automaticamente com o usuário que criou o registro.
    @ManyToOne //relacionamento com a entidade Usuario.
    @JoinColumn //cria a chave estrangeira no banco.
    private Usuario criadoPor;


    //Usuário que modificou por último
    @LastModifiedBy
    @ManyToOne
    @JoinColumn
    private Usuario ultimaModificacaoPor;


}



// O que é EntidadeAuditavel?
// É uma classe base abstrata (abstract) para entidades do sistema que precisam auditoria automática.
// Toda entidade que estender essa classe herda campos e comportamento de auditoria.
// Permite controlar:
// Quem criou o registro
// Quando foi criado
// Quem modificou
// Quando foi modificado
// Controle de versão (@Version)