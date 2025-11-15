package br.com.ifpe.oxefood.util.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Anotações da classe
@Getter //do lombok 
@Setter //lombok
@EqualsAndHashCode(of = { "id" })
@MappedSuperclass //indica que não cria uma tabela separada, mas as classes que herdarem dela terão os campos no banco.
public abstract class EntidadeNegocio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //o banco gera automaticamente o valor sequencial do id.
    private Long id;

    @JsonIgnore
    @Column
    private Boolean habilitado; //Indica se a entidade está ativa ou desativada.

}