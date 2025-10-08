package br.com.ifpe.oxefood.modelo.cidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CidadeService {
      @Transactional
    public void update(Long id, Cidade cidadeAlterado) {

        Cidade cidade = repository.findById(id).get();

        cidade.setNome(cidadeAlterado.getNome());
        cidade.setEstado(cidadeAlterado.getEstado());
        cidade.setQtdPopulacao(cidadeAlterado.getQtdPopulacao());
        cidade.setEhCapital(cidadeAlterado.getEhCapital());
        cidade.setDataFundacao(cidadeAlterado.getDataFundacao());
         repository.save(cidade);

    }
 @Transactional
   public void delete(Long id) {

       Cidade cidade = repository.findById(id).get();
       cidade.setHabilitado(Boolean.FALSE);

       repository.save(cidade);
   }



      @Autowired
    private CidadeRepository repository;

    @Transactional
    public Cidade save(Cidade cidade) {

        cidade.setHabilitado(Boolean.TRUE);
        return repository.save(cidade);

    }

    public List<Cidade> listarTodos() {

        return repository.findAll(); // SELECT * FROM CIDADE
    }

    public Cidade obterPorID(Long id) {

        return repository.findById(id).get(); // SELECT * FROM CIDADE WHERE ID =?
    }

}

