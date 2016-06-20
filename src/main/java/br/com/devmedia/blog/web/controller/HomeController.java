package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.service.PostagemService;

@Controller
@RequestMapping("/")
public class HomeController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PostagemService postagemService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(ModelMap model) {
        model.addAttribute("postagens", this.postagemService.findAll());
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "categoria/{link}", method = RequestMethod.GET)
    public ModelAndView postsByCategoria(@PathVariable("link") String link, ModelMap model) {
        model.addAttribute("postagens", this.postagemService.findByCategoria(link));
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "autor/{nome}", method = RequestMethod.GET)
    public ModelAndView postsByAutor(@PathVariable("nome") String nome, ModelMap model) {
        model.addAttribute("postagens", this.postagemService.findByAutor(nome));
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "{permalink}", method = RequestMethod.GET)
    public ModelAndView openPostagem(@PathVariable("permalink") String permalink, ModelMap model) {
        model.addAttribute("postagem", this.postagemService.findByPermalink(permalink));
        return new ModelAndView("post", model);
    }
}