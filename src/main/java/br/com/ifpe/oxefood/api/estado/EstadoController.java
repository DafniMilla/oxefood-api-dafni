
package br.com.ifpe.oxefood.api.estado;

import java.util.List;

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

import br.com.ifpe.oxefood.modelo.estado.Estado;
import br.com.ifpe.oxefood.modelo.estado.EstadoService;




//endpoints REST que o frontend vai chamar.
//comando pra rodar o docker:
//docker-compose up -d


@RestController
@RequestMapping("/api/estado") //endpoint pra testar no postman
@CrossOrigin

public class EstadoController {

    
    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@PathVariable("id") Long id, @RequestBody EstadoRequest request) {

        estadoService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

     @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       estadoService.delete(id);
       return ResponseEntity.ok().build();
   }


    @Autowired
    private EstadoService estadoService;

    //adicionar
    @PostMapping
    public ResponseEntity<Estado> save(@RequestBody EstadoRequest request) {

        Estado estado = estadoService.save(request.build());
        return new ResponseEntity<Estado>(estado, HttpStatus.CREATED);

    }
//retorna todos como uma listar
    @GetMapping
    public List<Estado> listarTodos() {
        return estadoService.listarTodos();
    }
//obter por id
    @GetMapping("/{id}")
    public Estado obterPorID(@PathVariable Long id) {
        return estadoService.obterPorID(id);
    }

}
