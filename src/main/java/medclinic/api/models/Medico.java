package medclinic.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medclinic.api.dto.AtualizaCadastroMedico;
import medclinic.api.dto.DadosCadastroMedico;
import medclinic.api.constants.Especialidade;

@Table(name ="medicos")         // Nome da tabela no bcando de dados
@Entity(name ="Medico")         // Nome da entidade
@Getter                         // Gera Getter
@NoArgsConstructor              // Para criar um construtor vazio usamos a anotação
@AllArgsConstructor             // construtor com todos os atributos da classe escrito
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    // Para que Endereço fique em uma classe separada, mas faça parte da mesma tabela de Médicos junto ao banco de dados.
    private EnderecoMedico enderecoMedico;

    private Boolean ativo = true;


    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.enderecoMedico = new EnderecoMedico(dados.endereco());

    }

    public void atualizarinformacoes(AtualizaCadastroMedico dados) {
        if (dados.nome() != null) {this.nome = dados.nome();}
        if (dados.telefone() != null) {this.telefone = dados.telefone();}
        if (dados.email() != null) {this.email = dados.email();}
        if (dados.endereco() != null) {this.enderecoMedico.atualizarinformacoes(dados.endereco());}
    }

    public void excluir() {
        this.ativo = false;
    }
}
