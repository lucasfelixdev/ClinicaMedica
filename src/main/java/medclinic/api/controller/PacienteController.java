package medclinic.api.controller;

import jakarta.validation.Valid;
import medclinic.api.models.medico.DadosListagemMedico;
import medclinic.api.models.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired // Deve estar acima da instância do repositório.
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastraPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }


//    @PostMapping
//    @Transactional
//    public void cadastraPacienteSemDTO(@RequestBody @Valid String cep, String nome, String telefone, String complemento, String email, String uf, String logradouro, String endereco)
//    {repository.save(new Paciente(cep,  nome,  telefone,  complemento,  email, uf,  logradouro, endereco));}


    @GetMapping
    public Page<dadosListagemPacientes> listarPacientes(@PageableDefault(size = 10, sort={"nome"})Pageable paginacao){
        return  repository.findAllByAtivoTrue(paginacao).map(dadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizaPaciente(@RequestBody @Valid AtualizaCadastroPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizainfos(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void exclusaoPacient(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

    }

}