package br.com.devmedia.blog.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.service.AvatarService;
import br.com.devmedia.blog.service.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AvatarService avatarService;
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario) {
        return new ModelAndView("usuario/cadastro");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("usuario") Usuario usuario, @RequestParam(value = "file", required = false) MultipartFile file) {
        Avatar avatar = this.avatarService.getAvatarByUpload(file);
        usuario.setAvatar(avatar);
        
        this.usuarioService.save(usuario);
        return "redirect:/usuario/perfil/" + usuario.getId();
    }
    
    @RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
    public ModelAndView perfil(@PathVariable("id") Long id) {
        Usuario usuario = this.usuarioService.findById(id);
        
        ModelAndView view = new ModelAndView("usuario/perfil");
        return view.addObject("usuario", usuario);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listUsuarios(ModelMap model) {
        List<Usuario> usuarios = this.usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        
        return new ModelAndView("usuario/list", model);
    }
}