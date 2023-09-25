package medclinic.api.models.paciente;

import jakarta.validation.constraints.NotNull;
import medclinic.api.models.EnderecoP.DadosEnderecoPaciente;

public record AtualizaCadastroPaciente(
        @NotNull
        Long id,
        String nome,
        String telfone,
        DadosEnderecoPaciente endereco) {
}
