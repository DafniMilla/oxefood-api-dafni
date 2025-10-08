package br.com.ifpe.oxefood.modelo.produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
@Service

public class CategoriaProdutoService {
     @Autowired //injetar automaticamente uma dependencia no atributo se precisar
   private CategoriaProdutoRepository repository;

   @Transactional //criar um bloco transacional, ou vai executar tudo ou não executa nada
   public CategoriaProduto save(CategoriaProduto CategoriaProduto) {

       CategoriaProduto.setHabilitado(Boolean.TRUE);
       return repository.save(CategoriaProduto); //cadastra no banco
   }

//listagem controller:
    public List<CategoriaProduto> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM Cliente
    }

    public CategoriaProduto obterPorID(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Ciente Where id = ? 
    }
//update:
//a partir do id q recebeu, consulta o cliente no banco e altera 
  @Transactional
   public void update(Long id, CategoriaProduto CategoriaProdutoAlterado) {

      CategoriaProduto categoriaProduto = repository.findById(id).get();
      categoriaProduto.setDescricao(CategoriaProdutoAlterado.getDescricao());

      //sem id: update
     //com id:insert
      repository.save(categoriaProduto);
  }
  
  //delete:
  //remoção lógica
  //sethabilitado: pra ver se está vivo ou não 
  //save = inclui(sem id) ou altera(com id)
  @Transactional
   public void delete(Long id) {

       CategoriaProduto categoriaProduto = repository.findById(id).get();
       categoriaProduto.setHabilitado(Boolean.FALSE);

       repository.save(categoriaProduto);
   }



    
}





  