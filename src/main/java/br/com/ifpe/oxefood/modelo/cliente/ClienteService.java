package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
   @Autowired
   private ClienteRepository repository;

   @Transactional  //cria um bloco transacional no metódo
   public Cliente save(Cliente cliente) {

       cliente.setHabilitado(Boolean.TRUE);
       return repository.save(cliente);
   }

   
    public List<Cliente> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM CLIENTE
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get();   //SELECT * FROM CLIENTE WHERE ID =?
    }

    }


