package ifrn.pi.ProjetoDPS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@PostMapping("/mercado/cadastro")
	public String cadastro() {
		return "Cadastro";
	}
	
	@PostMapping("/mercado/login")
	public String login() {
		return "Login";
	}
}