package medclinic.api.controller;

import jakarta.validation.Valid;
import medclinic.api.dto.*;
import medclinic.api.models.Paciente;
import medclinic.api.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired // Deve estar acima da instância do repositório.
    private PacienteRepository repository;

    // REQUISIÇÕES POST
    @PostMapping
    @Transactional
    public ResponseEntity cadastraPaciente(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhadosPaciente(paciente));
    }


    // REQUISIÇÕES GET
    @GetMapping
    public ResponseEntity<Page<dadosListagemPacientes>> listarPacientes(@PageableDefault(size = 11, sort={"nome"})Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(dadosListagemPacientes::new);
        return ResponseEntity.ok(page); // Return  204 status
    }


    @GetMapping("/{id}") // Detalhar dados paciente
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhadosPaciente(paciente));
    }


    // REQUISIÇÕES PUT
    @PutMapping
    @Transactional
    public ResponseEntity atualizaPaciente(@RequestBody @Valid AtualizaCadastroPaciente dados){

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizainfos(dados);

        return ResponseEntity.ok(new DadosDetalhadosPaciente(paciente));
    }

    // REQUISIÇÕES DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity exclusaoPacient(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        // Retornar 204
        return ResponseEntity.noContent().build();

    }

}