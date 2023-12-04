package medclinic.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medclinic.api.dto.DadosEnderecoPaciente;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EnderecoPaciente {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoPaciente(DadosEnderecoPaciente dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
    }

    public void atualizainfosendereco(DadosEnderecoPaciente endereco) {
        if(endereco.logradouro() != null){this.logradouro = endereco.logradouro();}
        if(endereco.numero() != null){this.numero = endereco.numero();}
        if(endereco.complemento() != null){this.complemento = endereco.complemento();}
        if(endereco.bairro() != null){this.bairro = endereco.bairro();}
        if(endereco.cidade() != null){this.cidade = endereco.cidade();}
        if(endereco.uf() != null){this.uf = endereco.uf();}
        if(endereco.cep() != null){this.cep = endereco.cep();}
    }
}
