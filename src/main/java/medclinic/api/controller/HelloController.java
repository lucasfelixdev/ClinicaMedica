package medclinic.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     //  Para indicar que a classe se trata de um controller API
@RequestMapping("/hello")        //  URL a qual esse controller irá responder (mapeamento)
public class HelloController {

    @GetMapping
    public String olaMundo(){
        return "Hello World Spring!";
    }

}
