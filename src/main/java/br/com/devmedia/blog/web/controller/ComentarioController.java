package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.devmedia.blog.entity.Comentario;
import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.service.ComentarioService;
import br.com.devmedia.blog.service.PostagemService;

@Controller
@RequestMapping("comentario")
public class ComentarioController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ComentarioService comentarioService;
    
    @Autowired
    private PostagemService postagemService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("comentario") Comentario comentario, @RequestParam("permalink") String permalink) {
        Postagem postagem = this.postagemService.findByPermalink(permalink);
        comentario.setPostagem(postagem);
        
        this.comentarioService.save(comentario);
        
        return "redirect:/" + permalink;
    }
}