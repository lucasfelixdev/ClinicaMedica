package medclinic.api.models.medico;

import jakarta.validation.constraints.NotNull;
import medclinic.api.models.enderecoMedico.DadosEndereco;
public record AtualizaCadastroMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco){
}
