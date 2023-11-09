package medclinic.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizaCadastroMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco){
}
