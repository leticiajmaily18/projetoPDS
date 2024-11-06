package ifrn.pi.ProjetoDPS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/mercado")
	public String index() {
		System.out.println("Chamou o index");
		return "Home";
	}
}
