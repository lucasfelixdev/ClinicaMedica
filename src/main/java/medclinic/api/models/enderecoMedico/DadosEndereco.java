package medclinic.api.models.enderecoMedico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// RECORD PARA RECEBER OS DADOS DO ENDERECO DO MEDICO.
public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank
        String complemento,
        String numero) {
}