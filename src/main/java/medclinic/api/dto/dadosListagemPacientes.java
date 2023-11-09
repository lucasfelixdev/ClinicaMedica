package medclinic.api.dto;

import medclinic.api.models.Paciente;

public record dadosListagemPacientes(Long id, String nome, String email, String cpf) {
    public dadosListagemPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
