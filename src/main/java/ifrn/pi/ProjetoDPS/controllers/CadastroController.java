package ifrn.pi.ProjetoDPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ifrn.pi.ProjetoDPS.models.Funcionario;
import ifrn.pi.ProjetoDPS.repositories.CadastroRepository;

@Controller
public class CadastroController {

    @Autowired
    private CadastroRepository cr;
    
    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "Cadastro";
    }
    
    @GetMapping("/mercado/cadastro")
    public String form() {
        return "Cadastro";
    }

    @PostMapping("/mercado/cadastro")
    public String cadastro(Funcionario funcionario) {
        cr.save(funcionario);
        return "redirect:/mercado/login";
    }
}
