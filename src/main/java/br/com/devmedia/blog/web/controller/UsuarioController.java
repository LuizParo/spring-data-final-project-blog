package br.com.devmedia.blog.web.controller;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Perfil;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.service.AvatarService;
import br.com.devmedia.blog.service.UsuarioService;
import br.com.devmedia.blog.web.editor.PerfilEditorSupport;
import br.com.devmedia.blog.web.validator.UsuarioValidator;

@Controller
@RequestMapping("usuario")
public class UsuarioController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AvatarService avatarService;
    
    @InitBinder(value = "usuario")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Perfil.class, new PerfilEditorSupport());
        binder.setValidator(new UsuarioValidator());
    }
    
    @RequestMapping(value = {"/update/{id}", "/update"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView update(@PathVariable("id") Optional<Long> id, @ModelAttribute("usuario") @Validated Usuario usuario, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if(id.isPresent()) {
            usuario = this.usuarioService.findById(id.get());
            view.addObject("usuario", usuario);
            view.setViewName("usuario/atualizar");
            return view;
        }
        
        if(result.hasErrors()) {
            view.setViewName("usuario/atualizar");
            return view;
        }
        
        this.usuarioService.updateNomeAndEmail(usuario);
        view.setViewName("redirect:/usuario/perfil/" + usuario.getId());
        return view;
    }
    
    @RequestMapping(value = {"/update/senha/{id}", "/update/senha"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateSenha(@PathVariable("id") Optional<Long> id, @ModelAttribute("usuario") @Validated Usuario usuario, BindingResult result) {
        ModelAndView view = new ModelAndView();
        
        if(id.isPresent()) {
            usuario = this.usuarioService.findById(id.get());
            view.addObject("usuario", usuario);
            view.setViewName("usuario/atualizar");
            return view;
        }
        
        if(result.hasFieldErrors("senha")) {
            usuario = this.usuarioService.findById(usuario.getId());
            view.setViewName("usuario/atualizar");
            view.addObject("nome", usuario.getNome());
            view.addObject("email", usuario.getEmail());
            return view;
        }
        
        this.usuarioService.updateSenha(usuario);
        view.setViewName("redirect:/usuario/perfil/" + usuario.getId());
        return view;
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario) {
        return new ModelAndView("usuario/cadastro");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("usuario") @Validated Usuario usuario, BindingResult result) {
        if(result.hasErrors()) {
            return "usuario/cadastro";
        }
        
        Avatar avatar = this.avatarService.getAvatarByUpload(usuario.getFile());
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
        model.addAttribute("page", this.usuarioService.findByPagination(0, 5));
        model.addAttribute("urlPagination", "/usuario/page/");
        
        return new ModelAndView("usuario/list", model);
    }
    
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ModelAndView pageUsuarios(@PathVariable("page") Integer pagina) {
        ModelAndView view = new ModelAndView("usuario/list");
        view.addObject("page", this.usuarioService.findByPagination(pagina - 1, 5));
        view.addObject("urlPagination", "/usuario/page/");
        
        return view;
    }
    
    @RequestMapping(value = "/sort/{order}/{field}/page/{page}", method = RequestMethod.GET)
    public ModelAndView pageUsuario(@PathVariable("page") Integer pagina, @PathVariable("order") String order, @PathVariable("field") String field) {
        ModelAndView view = new ModelAndView("usuario/list");
        view.addObject("page", this.usuarioService.findByPaginationOrderByField(pagina - 1, 5, field, order));
        view.addObject("urlPagination", "/usuario/sort/" + order + "/" + field + "/page");
        
        return view;
    }
}