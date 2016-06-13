package br.com.devmedia.blog.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("avatar")
public class AvatarController {
    private static final Logger LOG = Logger.getLogger(AvatarController.class);
    
    @Autowired
    private AvatarService avatarService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> loadAvatar(@PathVariable("id") Long id) {
        Avatar avatar = this.avatarService.findById(id);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(avatar.getTipo()));
        
        try(InputStream is = new ByteArrayInputStream(avatar.getAvatar())) {
            return new ResponseEntity<>(IOUtils.toByteArray(is), headers, HttpStatus.OK);
        } catch (IOException e) {
            LOG.error("Ocorreu um erro ao recuperar o Avatar: ", e.getCause());
            return null;
        }
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id, @ModelAttribute("avatar") Avatar avatar) {
        ModelAndView view = new ModelAndView("avatar/atualizar");
        view.addObject("id", id);
        
        return view;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("avatar") Avatar avatar, @RequestParam("file") MultipartFile file) {
        Avatar avatarUploaded = this.avatarService.getAvatarByUpload(file);
        avatarUploaded.setId(avatar.getId());
        this.avatarService.saveOrUpdate(avatarUploaded);
        
        Usuario usuario = this.usuarioService.findByAvatar(avatarUploaded);
        return "redirect:/usuario/perfil/" + usuario.getId();
    }
}