package medclinic.api.dto;

import medclinic.api.models.EnderecoPaciente;
import medclinic.api.models.Paciente;

public record DadosDetalhadosPaciente(Long id, String nome, String telefone, String email, String cpf, EnderecoPaciente enderecoPaciente) {
    public DadosDetalhadosPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
