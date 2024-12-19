package ifrn.pi.ProjetoPBD.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.ProjetoPBD.modelos.Estoque;
import ifrn.pi.ProjetoPBD.repositories.EstoqueRepository;

@Controller
@RequestMapping("/Estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoquerepository;

    @GetMapping("/form")
    public String form(Estoque estoque) {
        return "Estoque/Gerenciador_do_Estoque";
    }

    @PostMapping
    public String Salvar(Estoque estoque) {
        estoquerepository.save(estoque);
        return "redirect:/Estoque";
    }

    @GetMapping
    public ModelAndView listar() {
        List<Estoque> listagenDOEstoque = estoquerepository.findAll();
        List<Estoque> produtosEsgotados = estoquerepository.findByQuantidade(0);  // Produtos com quantidade zero
        ModelAndView mv = new ModelAndView("Estoque/Gerenciador_do_Estoque");
        mv.addObject("listagenDOEstoque", listagenDOEstoque);
        mv.addObject("produtosEsgotados", produtosEsgotados);  // Lista de produtos esgotados
        mv.addObject("estoque", new Estoque());
        return mv;
    }

    @GetMapping("/{id}/removerP")
    public String apagardoEstoque(@PathVariable Long id) {
        Optional<Estoque> opt = estoquerepository.findById(id);
        if (opt.isPresent()) {
            Estoque estoque = opt.get();
            estoquerepository.delete(estoque);
        }
        return "redirect:/Estoque";
    }

    @GetMapping("/{id}/selecionarP")
    public ModelAndView selecionarEstoque(@PathVariable Long id) {
        ModelAndView md = new ModelAndView();
        Optional<Estoque> opt = estoquerepository.findById(id);
        if (opt.isEmpty()) {
            md.setViewName("redirect:/Estoque");
            return md;
        }
        Estoque estoque = opt.get();
        md.setViewName("Estoque/Gerenciador_do_Estoque");
        md.addObject("estoque", estoque);
        return md;
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

    @GetMapping("/estoque/esgotados")
    public ModelAndView listarProdutosEsgotados() {
        List<Estoque> produtosEsgotados = estoquerepository.findByQuantidade(0);  // Consulta para produtos esgotados
        
        ModelAndView mv = new ModelAndView("Estoque/Gerenciador_do_Estoque");
        mv.addObject("produtosEsgotados", produtosEsgotados);  // Passa os produtos esgotados
        return mv;
    }
}
