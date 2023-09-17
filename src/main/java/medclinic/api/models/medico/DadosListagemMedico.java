package medclinic.api.models.medico;
import medclinic.api.models.medico.Especialidade;

// RECORD PARA LISTAR OS MÉDICOS
public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}

