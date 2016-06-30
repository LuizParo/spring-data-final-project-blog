package br.com.devmedia.blog.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.service.CategoriaService;
import br.com.devmedia.blog.service.PostagemService;
import br.com.devmedia.blog.web.editor.CategoriaEditorSupport;

@Controller
@RequestMapping("postagem")
public class PostagemController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PostagemService postagemService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, new CategoriaEditorSupport(List.class, this.categoriaService));
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView form(@ModelAttribute("postagem") Postagem postagem) {
        ModelAndView view = new ModelAndView("postagem/cadastro");
        view.addObject("categorias", this.categoriaService.findAll());
        
        return view;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("postagem") Postagem postagem) {
        this.postagemService.saveOrUpdate(postagem);
        return "redirect:/postagem/list";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model) {
        model.addAttribute("page", this.postagemService.findByPagination(0, 5));
        
        // No necessary with ajax.
        //model.addAttribute("urlPagination", "/postagem/page");
        return new ModelAndView("postagem/list", model);
    }
    
    @RequestMapping(value = "/ajax/page/{page}", method = RequestMethod.GET)
    public ModelAndView pagePostagens(@PathVariable("page") Integer pagina) {
        ModelAndView view = new ModelAndView("postagem/table-rows");
        view.addObject("page", this.postagemService.findByPagination(pagina - 1, 5));
        
        // No necessary with ajax.
        //view.addObject("urlPagination", "/postagem/page");
        return view;
    }
    
    @RequestMapping(value = "/ajax/titulo/{titulo}/page/{page}", method = RequestMethod.GET)
    public ModelAndView searchByAjax(@PathVariable("titulo") String titulo, @PathVariable("page") Integer pagina) {
        ModelAndView view = new ModelAndView("postagem/table-rows");
        view.addObject("page", this.postagemService.findByTitulo(pagina - 1, 5, titulo));
        return view;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        this.postagemService.delete(id);
        return "redirect:/postagem/list";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
        Postagem postagem = this.postagemService.findById(id);
        model.addAttribute("postagem", postagem);
        model.addAttribute("categorias", this.categoriaService.findAll());
        
        return new ModelAndView("postagem/cadastro", model);
    }
}