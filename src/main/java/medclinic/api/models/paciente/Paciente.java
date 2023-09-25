package medclinic.api.models.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medclinic.api.models.EnderecoP.EnderecoPaciente;

@Table(name="pacientes")
@Entity(name="Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private EnderecoPaciente endereco;
    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new EnderecoPaciente(dados.endereco());}

    public void atualizainfos(AtualizaCadastroPaciente dados) {
        if (dados.nome() != null) {this.nome = dados.nome();}
        if (dados.telfone() != null) {this.telefone = dados.telfone();}
        if (dados.endereco() != null) {this.endereco.atualizainfosendereco(dados.endereco());}}

    public void excluir() {this.ativo = false;}
}