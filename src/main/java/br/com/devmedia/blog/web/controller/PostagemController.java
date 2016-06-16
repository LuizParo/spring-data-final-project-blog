package br.com.devmedia.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.service.PostagemService;

@Controller
@RequestMapping("postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView form(@ModelAttribute("postagem") Postagem postagem) {
        return new ModelAndView("postagem/cadastro");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("postagem") Postagem postagem) {
        this.postagemService.saveOrUpdate(postagem);
        return "redirect:/postagem/list";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model) {
        model.addAttribute("postagens", this.postagemService.findAll());
        return new ModelAndView("postagem/list", model);
    }
}