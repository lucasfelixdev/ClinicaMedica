package medclinic.api.controller;

import jakarta.validation.Valid;
import medclinic.api.dto.AtualizaCadastroMedico;
import medclinic.api.dto.DadosListagemMedico;
import medclinic.api.dto.DadosCadastroMedico;
import medclinic.api.models.Medico;
import medclinic.api.Repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController                     //  Indicar que a classe se trata de um controller API
@RequestMapping("/medico")       // URL a qual esse controller irá responder (mapeamento)
public class MedicoController {

    @Autowired // Injeção de dependências
    private MedicoRepository repository; // Instância da interface que contém os métodos JPA para acessar o BD
    @PostMapping          //  Para requisições do tipo Post Método
    @Transactional          // Transação ativa com o banco de dados
    // RequestBody para puxar os dados Json do corpo da requisição.
    // Como parâmetro foi passado uma classe Record contendo todos os dados dos requisitos da classe Médico
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }


    // Retorno por Paginação
    @GetMapping
    // Default da paginação é = 10 sendo ordenado pelo nome.
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        // http://localhost:8080/medicos?sort=nome - Listando pro ordem crescente de nome
        // http://localhost:8080/medicos?sort=crm,desc - Por ordem decrescento
    }

    // Retorno por listagem
//    @GetMapping

//    public List<DadosListagemMedico> listarMedicos(){
//       return repository.findAll().stream().map(DadosListagemMedico::new).toList();} // .stream()  permite operações de processamento de dados em uma coleção de forma mais flexível e funcional.

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid AtualizaCadastroMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarinformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void exclusaoMed(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
