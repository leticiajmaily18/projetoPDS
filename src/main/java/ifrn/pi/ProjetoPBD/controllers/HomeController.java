package ifrn.pi.ProjetoPBD.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.ProjetoPBD.modelos.Estoque;
import ifrn.pi.ProjetoPBD.repositories.EstoqueRepository;

@Controller
public class HomeController {

    @Autowired
    private EstoqueRepository estoquerepository;

    @GetMapping("/home")
    public ModelAndView home() {
        List<Estoque> listagenDOEstoque = estoquerepository.findAll();
        ModelAndView mv = new ModelAndView("Home/home");
        mv.addObject("listagenDOEstoque", listagenDOEstoque);
        return mv;
    }

    @GetMapping("/produto/{id}")
    public ModelAndView produto(@PathVariable("id") Long id) {
        Optional<Estoque> produto = estoquerepository.findById(id);
        ModelAndView mv = new ModelAndView("Home/produto");
        if (produto.isPresent()) {
            mv.addObject("produto", produto.get());
        } else {
            mv.addObject("erro", "Produto não encontrado");
        }
        return mv;
    }

    @GetMapping("/comprar/{id}")
    public ModelAndView comprarProduto(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home");

        Optional<Estoque> opt = estoquerepository.findById(id);
        if (opt.isPresent()) {
            Estoque produto = opt.get();
            if (produto.getQuantidade() > 0) {
                produto.setQuantidade(produto.getQuantidade() - 1);
                estoquerepository.save(produto);
            } else {
                mv.setViewName("redirect:/Estoque");
            }
        }

        return mv;
    }
}
