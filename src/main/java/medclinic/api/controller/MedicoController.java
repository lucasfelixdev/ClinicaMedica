package medclinic.api.controller;

import medclinic.api.Dtos.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     //  Indicar que a classe se trata de um controller API
@RequestMapping("/medico")       // URL a qual esse controller irá responder (mapeamento)
public class MedicoController {

    @PostMapping                //  Para requisições do tipo Post Método

    // RequestBody para puxar os dados Json do corpo da requisição.
    // Como parâmetro foi passado uma classe Record contendo todos os dados dos requisitos da classe Médico
    public void cadastrar(@RequestBody DadosCadastroMedico dados){
        System.out.println(dados);
    }

}
