package medclinic.api.dto;
import medclinic.api.constants.Especialidade;
import medclinic.api.models.EnderecoMedico;
import medclinic.api.models.Medico;

public record DadosDetalhadosMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, EnderecoMedico endereco) {
    public DadosDetalhadosMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEnderecoMedico());
    }
}
