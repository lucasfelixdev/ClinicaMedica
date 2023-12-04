package medclinic.api.controller;

import jakarta.validation.Valid;
import medclinic.api.dto.AtualizaCadastroMedico;
import medclinic.api.dto.DadosDetalhadosMedico;
import medclinic.api.dto.DadosListagemMedico;
import medclinic.api.dto.DadosCadastroMedico;
import medclinic.api.models.Medico;
import medclinic.api.Repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController                     //  Indicar que a classe se trata de um controller API
@RequestMapping("/medico")       // URL a qual esse controller irá responder (mapeamento)
@CrossOrigin(origins = "http://localhost:3000")
public class MedicoController {



    @Autowired // Injeção de dependências
    private MedicoRepository repository; // Instância da interface que contém os métodos JPA para acessar o


    // REQUISIÇÕES POST
    @PostMapping
    @Transactional     // Transação ativa com o banco de dados
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhadosMedico(medico));
    }


    // REQUISIÇÕES GET

    // RETORNA LISTA POR PAGINAÇÃO
    @GetMapping
    // Default da paginação é = 10 sendo ordenado pelo nome.
    public ResponseEntity<Page<DadosListagemMedico>>listarMedicos(@PageableDefault(size = 11, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}") // Detalhar dados médico
    public ResponseEntity detalharMed(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhadosMedico(medico));
    }


    // REQUISIÇÕES PUT
    @RequestMapping(method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizaCadastroMedico dados){
        var medico = repository.getReferenceById(dados.id());


        medico.atualizarinformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhadosMedico(medico));
    }


    // REQUISIÇÕES DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity exclusaoMed(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        // Retornar 204
        return ResponseEntity.noContent().build();
    }

}
