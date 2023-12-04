package medclinic.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizaCadastroPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoPaciente endereco) {
}
