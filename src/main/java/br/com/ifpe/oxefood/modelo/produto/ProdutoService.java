package br.com.ifpe.oxefood.modelo.produto;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class ProdutoService { 
    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto save(Produto produto){
        produto.setHabilitado(Boolean.TRUE);
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
  
        return repository.findAll();// select * from cliente
    }

    public Produto obterPorID(Long id) {

        return repository.findById(id).get(); // select * from cliente wher id =?
    }

    @Transactional // confirma as alteração no final do banco 
   public void update(Long id, Produto produtoAlterado) {

      Produto produto = repository.findById(id).get();
       produto.setCategoria(produtoAlterado.getCategoria());
      produto.setTitulo(produtoAlterado.getTitulo());
      produto.setDescricao(produtoAlterado.getDescricao());
      produto.setValorUnitario(produtoAlterado.getValorUnitario());
      produto.setCodigo(produtoAlterado.getCodigo());
      produto.setTempoEntregaMinimo(produtoAlterado.getTempoEntregaMinimo());
      produto.setTempoEntregaMaximo(produtoAlterado.getTempoEntregaMaximo());
	    
      repository.save(produto);
  }

  @Transactional
   public void delete(Long id) {

       Produto produto = repository.findById(id).get();
       produto.setHabilitado(Boolean.FALSE);

       repository.save(produto);
   }


}