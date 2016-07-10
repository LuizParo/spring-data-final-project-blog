package br.com.devmedia.blog.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Comentario;
import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.entity.UsuarioLogado;
import br.com.devmedia.blog.service.ComentarioService;
import br.com.devmedia.blog.service.PostagemService;
import br.com.devmedia.blog.service.UsuarioService;

@Controller
@RequestMapping("comentario")
public class ComentarioController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ComentarioService comentarioService;
    
    @Autowired
    private PostagemService postagemService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("comentario") @Validated Comentario comentario, BindingResult result,
                             @RequestParam("permalink") String permalink,
                             @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Postagem postagem = this.postagemService.findByPermalink(permalink);
        comentario.setPostagem(postagem);
        
        if(result.hasErrors()) {
            return new ModelAndView("post", "postagem", postagem);
        }
        
        comentario.setUsuario(this.usuarioService.findById(usuarioLogado.getId()));
        this.comentarioService.save(comentario);
        return new ModelAndView("redirect:/" + permalink);
    }
}