package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  
}

//ClienteRepository é a interface responsável por acessar o banco de dados da entidade Cliente.
//Ela estende JpaRepository

// Métodos que já vêm prontos:

// save(cliente) — salva ou atualiza

// findById(id) — busca por ID

// findAll() — lista todos

// deleteById(id) — remove pelo ID

// count() — conta registros

// <Cliente, Long> indica:

// Cliente → é a entidade que será manipulada

// Long → é o tipo da chave primária (ID) da entidade

// Ou seja:
// O repositório vai trabalhar com registros da tabela Cliente, onde o ID é do tipo Long.


//===========
//O Spring injeta esse repositório na Service: