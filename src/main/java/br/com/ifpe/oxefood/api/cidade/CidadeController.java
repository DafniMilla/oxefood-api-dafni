package br.com.ifpe.oxefood.api.cidade;
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

import br.com.ifpe.oxefood.modelo.cidade.Cidade;
import br.com.ifpe.oxefood.modelo.cidade.CidadeService;





@RestController
@RequestMapping("/api/cidade") //endpoint pra testar no postman
@CrossOrigin

public class CidadeController {
      
    @PutMapping("/{id}")
    public ResponseEntity<Cidade> update(@PathVariable("id") Long id, @RequestBody CidadeRequest request) {

        cidadeService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

     @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       cidadeService.delete(id);
       return ResponseEntity.ok().build();
   }


    @Autowired
    private CidadeService cidadeService;

    //adicionar
    @PostMapping
    public ResponseEntity<Cidade> save(@RequestBody CidadeRequest request) {

        Cidade cidade = cidadeService.save(request.build());
        return new ResponseEntity<Cidade>(cidade, HttpStatus.CREATED);

    }
//retorna todos como uma listar
    @GetMapping
    public List<Cidade> listarTodos() {
        return cidadeService.listarTodos();
    }
//obter por id
    @GetMapping("/{id}")
    public Cidade obterPorID(@PathVariable Long id) {
        return cidadeService.obterPorID(id);
    }
}
