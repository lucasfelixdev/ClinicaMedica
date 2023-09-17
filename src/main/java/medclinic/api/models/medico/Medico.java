package medclinic.api.models.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medclinic.api.models.enderecoMedico.Endereco;

@Table(name ="medicos")         // Nome da tabela no bcando de dados
@Entity(name ="Medico")         // Nome da entidade
@Getter                         // Gera Getter
@NoArgsConstructor              // Para criar um construtor vazio usamos a anotação
@AllArgsConstructor             // construtor com todos os atributos da classe escrito
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // Para que Endereço fique em uma classe separada, mas faça parte da mesma tabela de Médicos junto ao banco de dados.
    private Endereco endereco;


    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }
}
