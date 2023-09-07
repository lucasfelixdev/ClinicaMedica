package medclinic.api.models.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medclinic.api.models.endereco.DadosEndereco;

// 1- DTO - DATA TRANSFER OBJECT.
// 2- Atributo "Especialidades" são fixos então usamos o ENUM onde armazena as constantes, trabalhadas pela clínica.
// 3- Recebe "DadosEndereço" como objeto onde contém todas as infos de endereço sem estender em demasia o parâmetro do código.
public record DadosCadastroMedico(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp ="\\d{4,6}")
        String crm,

        @NotNull
        Especialidades especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
