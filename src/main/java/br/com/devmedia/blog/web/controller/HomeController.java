package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.devmedia.blog.entity.Comentario;
import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.service.PostagemService;

@Controller
@RequestMapping("/")
public class HomeController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PostagemService postagemService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(ModelMap model) {
        model.addAttribute("page", this.postagemService.findByPagination(0, 5));
        model.addAttribute("urlPagination", "/page");
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "page/{page}", method = RequestMethod.GET)
    public ModelAndView pageHome(@PathVariable("page") Integer pagina, ModelMap model) throws NoHandlerFoundException {
        Page<Postagem> page = this.postagemService.findByPagination(pagina - 1, 5);
        if(page.getContent().isEmpty()) {
            throw new NoHandlerFoundException(null, null, null);
        }
        
        model.addAttribute("page", page);
        model.addAttribute("urlPagination", "/page");
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "categoria/{link}/page/{page}", method = RequestMethod.GET)
    public ModelAndView postsByCategoria(@PathVariable("page") Integer pagina, @PathVariable("link") String link, ModelMap model) {
        model.addAttribute("page", this.postagemService.findByPaginationByCategoria(pagina - 1, 5, link));
        model.addAttribute("urlPagination", "/categoria/" + link + "/page");
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "autor/{id}/page/{page}", method = RequestMethod.GET)
    public ModelAndView postsByAutor(@PathVariable("id") Long autorId, @PathVariable("page") Integer pagina, ModelMap model) {
        model.addAttribute("page", this.postagemService.findByPaginationByAutor(pagina - 1, 5, autorId));
        model.addAttribute("urlPagination", "/autor/" + autorId + "/page");
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "{permalink}", method = RequestMethod.GET)
    public ModelAndView openPostagem(@ModelAttribute("comentario") Comentario comentario, @PathVariable("permalink") String permalink, ModelMap model) throws NoHandlerFoundException {
        Postagem postagem = this.postagemService.findByPermalink(permalink);
        if(postagem == null) {
            throw new NoHandlerFoundException(null, null, null);
        }
        
        model.addAttribute("postagem", postagem);
        return new ModelAndView("post", model);
    }
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("texto") String texto, ModelMap model) {
        model.addAttribute("page", this.postagemService.findByTexto(0, 5, texto));
        model.addAttribute("urlPagination", "/search/texto/" + texto + "/page");
        return new ModelAndView("posts", model);
    }
    
    @RequestMapping(value = "search/texto/{texto}/page/{page}", method = RequestMethod.GET)
    public ModelAndView search(@PathVariable("texto") String texto, @PathVariable("page") Integer pagina, ModelMap model) {
        model.addAttribute("page", this.postagemService.findByTexto(pagina - 1, 5, texto));
        model.addAttribute("urlPagination", "/search/texto/" + texto + "/page");
        return new ModelAndView("posts", model);
    }
}