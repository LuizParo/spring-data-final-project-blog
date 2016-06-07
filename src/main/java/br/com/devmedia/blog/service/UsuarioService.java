package br.com.devmedia.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.repository.UsuarioRepository;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    public List<Usuario> findAll() {
        return this.repository.findAll();
    }
    
    public Usuario findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
    
    public Usuario findByAvatar(Avatar avatar) {
        return this.repository.findByAvatar(avatar);
    }
    
    public Usuario findById(Long id) {
        return this.repository.findOne(id);
    }
}