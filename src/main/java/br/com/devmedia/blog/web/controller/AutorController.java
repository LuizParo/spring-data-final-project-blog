package br.com.devmedia.blog.web.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    
    @RequestMapping(value = {"/perfil/{id}", "/list"}, method = RequestMethod.GET)
    public ModelAndView getAutor(@PathVariable("id") Optional<Long> id) {
        ModelAndView view = new ModelAndView("autor/perfil");
        
        if(id.isPresent()) {
            Autor autor = this.autorService.findById(id.get());
            view.addObject("autores", Arrays.asList(autor));
            return view;
        }
        view.addObject("page", this.autorService.findByPagination(0, 5));
        view.addObject("urlPagination", "/autor/page");
        return view;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id) {
        Autor autor = this.autorService.findById(id);
        
        ModelAndView view = new ModelAndView("autor/cadastro");
        view.addObject("autor", autor);
        return view;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("autor") @Validated Autor autor, BindingResult result) {
        if(result.hasErrors()) {
            return "autor/cadastro";
        }
        this.autorService.save(autor);
        return "redirect:/autor/perfil/" + autor.getId();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        this.autorService.delete(id);
        return "redirect:/autor/form";
    }
    
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ModelAndView pageAutores(@PathVariable("page") Integer pagina) {
        ModelAndView view = new ModelAndView("autor/perfil");
        view.addObject("page", this.autorService.findByPagination(pagina - 1, 5));
        view.addObject("urlPagination", "/autor/page");
        return view;
    }
}