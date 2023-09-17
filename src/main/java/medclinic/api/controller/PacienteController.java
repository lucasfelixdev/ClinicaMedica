package medclinic.api.controller;

import jakarta.validation.Valid;
import medclinic.api.models.paciente.DadosCadastroPaciente;
import medclinic.api.models.paciente.Paciente;
import medclinic.api.models.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired // Deve estar acima da instância do repositório.
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastraPaciente(@RequestBody @Valid DadosCadastroPaciente dados){repository.save(new Paciente(dados));}
}
