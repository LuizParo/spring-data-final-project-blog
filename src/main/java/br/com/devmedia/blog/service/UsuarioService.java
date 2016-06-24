package br.com.devmedia.blog.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
    
    public Page<Usuario> findByPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return this.repository.findAllByOrderByNomeAsc(pageable);
    }
    
    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.repository.delete(id);
    }
    
    @Transactional(readOnly = false)
    public void save(Usuario usuario) {
        if(usuario.getDataCadastro() == null) {
            usuario.setDataCadastro(LocalDate.now());
        }
        
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        repository.save(usuario);
    }

    @Transactional(readOnly = false)
    public void updateNomeAndEmail(Usuario usuario) {
        this.repository.updateNomeAndEmail(usuario.getNome(), usuario.getEmail(), usuario.getId());
    }

    @Transactional(readOnly = false)
    public void updateSenha(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.updateSenha(usuario.getSenha(), usuario.getId());
    }
}