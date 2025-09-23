package br.com.ifpe.oxefood.modelo.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    
    @Autowired
   private ProdutoRepository repository;

   @Transactional  //cria um bloco transacional no metódo
   public Produto save(Produto produto) {

       produto.setHabilitado(Boolean.TRUE);
       return repository.save(produto);
   }
    public List<Produto> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM CLIENTE
    }

    public Produto obterPorID(Long id) {

        return repository.findById(id).get();   //SELECT * FROM CLIENTE WHERE ID =?
    }
}
