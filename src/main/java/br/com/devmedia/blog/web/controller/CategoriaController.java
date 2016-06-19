package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Categoria;
import br.com.devmedia.blog.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView cadastro(@ModelAttribute("categoria") Categoria categoria) {
        ModelAndView view = new ModelAndView("categoria/cadastro");
        view.addObject("categorias", this.categoriaService.findAll());
        
        return view;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("categoria") Categoria categoria) {
        this.categoriaService.saveOrUpdate(categoria);
        return "redirect:/categoria/form";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
        return "redirect:/categoria/form";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("categoria", this.categoriaService.findById(id));
        model.addAttribute("categorias", this.categoriaService.findAll());
        
        return new ModelAndView("categoria/cadastro", model);
    }
}