package medclinic.api.dto;
import medclinic.api.constants.Especialidade;
import medclinic.api.models.Medico;

// RECORD PARA LISTAR OS MÉDICOS
public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}

