package medclinic.api.Dtos;

//DTO - DATA TRANSFER OBJECT
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
 // Especialidades são fixos então usamos o ENUM onde armazena as constantes, trabalhadas pela clínica
 // Recebe DadosEndereço como objeto onde contém todas as infos de endereço sem estender em demasia o parâmetro do código

}
