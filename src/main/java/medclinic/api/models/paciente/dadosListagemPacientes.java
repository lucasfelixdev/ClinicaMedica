package medclinic.api.models.paciente;

public record dadosListagemPacientes(Long id, String nome, String email, String cpf) {
    public dadosListagemPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
