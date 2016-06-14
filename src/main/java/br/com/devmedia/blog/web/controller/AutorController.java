package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Autor;
import br.com.devmedia.blog.service.AutorService;

@Controller
@RequestMapping("autor")
public class AutorController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AutorService autorService;
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView shorForm(@ModelAttribute("autor") Autor autor) {
        return new ModelAndView("autor/cadastro");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("autor") Autor autor) {
        this.autorService.save(autor);
        return "redirect:/autor/perfil/" + autor.getId();
    }
    
    @RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
    public ModelAndView getAutor(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("autor/perfil");
        
        Autor autor = this.autorService.findById(id);
        view.addObject("autor", autor);
        
        return view;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id) {
        Autor autor = this.autorService.findById(id);
        
        ModelAndView view = new ModelAndView("autor/cadastro");
        view.addObject("autor", autor);
        return view;
    }
}