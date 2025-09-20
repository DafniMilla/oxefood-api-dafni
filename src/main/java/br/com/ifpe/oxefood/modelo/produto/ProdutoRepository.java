package br.com.ifpe.oxefood.modelo.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

public class ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
