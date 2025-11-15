package br.com.ifpe.oxefood.api.cliente;

import java.util.List;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController // Anotações do Controller: tudo que ela retorna vira json
@RequestMapping("/api/cliente") // Anotações do Controller: Define o caminho base da API.
@CrossOrigin // @CrossOrigin: Permite chamadas de outros domínios

// @Tag(...) Usado para documentação do Swagger

@Tag(name = "API Cliente", description = "API responsável pelos servidos de cliente no sistema")

public class ClienteController {

    // Injeção de dependência
    @Autowired
    private UsuarioService usuarioService; // obtém usuário logado

    @Autowired
    private ClienteService clienteService; // regras de negócio (salvar, listar, atualizar, excluir cliente etc.)

    Operations(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )

// @RequestBody → pega os dados enviados no corpo da requisição
// @Valid → valida os campos (Spring Validation)
// HttpServletRequest → usado para pegar o usuário logado

    @PostMapping // cria novo cliente
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request, HttpServletRequest req) {

        Cliente cliente = clienteService.save(request.build(), usuarioService.obterUsuarioLogado(req));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @GetMapping  //Retorna lista de todos os clientes.
    public List<Cliente> listarTodos() {

        return clienteService.listarTodos();
    }


    //@PathVariable → pega o ID da URL 
    //Busca um cliente específico.

    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {

        return clienteService.obterPorID(id);
    }

    @PutMapping("/{id}") //Atualiza os dados do cliente
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id,
            @RequestBody @Valid ClienteRequest request, HttpServletRequest req) {

        clienteService.update(id, request.build(), usuarioService.obterUsuarioLogado(req));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/endereco/{clienteId}") // um endereço e associa a um cliente.
    public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId,
            @RequestBody EnderecoClienteRequest request) {

        EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
        return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
    }

    @PutMapping("/endereco/{enderecoId}") //Atualiza um endereço pelo ID.
    public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId,
            @RequestBody EnderecoClienteRequest request) {

        EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
        return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
    }

    @DeleteMapping("/endereco/{enderecoId}")
    public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

        clienteService.removerEnderecoCliente(enderecoId);
        return ResponseEntity.noContent().build();
    }

}



// É um Controller REST do Spring Boot.

// Define endpoints para CRUD de Cliente e CRUD de Endereços.

// Usa ClienteService e UsuarioService para regras de negócio e autenticação.

// Métodos usam POST, GET, PUT, DELETE.

// @Valid garante validações.

// @PathVariable pega dados da URL.

// @RequestBody pega dados do corpo da requisição.

// Retorna ResponseEntity para controlar status HTTP.
