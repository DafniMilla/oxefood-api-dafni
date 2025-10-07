package br.com.ifpe.oxefood.modelo.produto;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class CategoriaProdutoService {
    @Autowired
    private CategoriaProduto repository;

    @Transactional
    public CategoriaProduto save(CategoriaProduto categoriaProduto){
        CategoriaProduto.setHabilitado(Boolean.TRUE);
    }

    public List<CategoriaProduto> listarTodos(){
        return repository.findAll();
    }

    public CategoriaProduto obterPorID(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, CategoriaProduto categoriaProdutoAlterado){
        CategoriaProduto categoriaProduto = repository.findById(id).get();
        categoriaProduto.setDescricao(categoriaProdutoAlterado.getDescricao());
        

        repository.save(categoriaProduto);
    }

    @Transactional
    public void delete(Long id){
        CategoriaProduto categoriaProduto = repository.findById(id).get();
        categoriaProduto.setHabilitado(Boolean.FALSE);

        repository.save(categoriaProduto);
    }
    
}