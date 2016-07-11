package br.com.devmedia.blog.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Perfil;
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
    
    public Page<Usuario> findByPaginationOrderByField(int page, int size, String field, String order) {
        Sort sort = new Sort(new Order(Direction.fromString(order), field));
        return this.repository.findAll(new PageRequest(page, size, sort));
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
        usuario.setPerfil(Perfil.LEITOR);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        repository.save(usuario);
    }

    @Transactional(readOnly = false)
    public void updateNomeAndEmail(Usuario usuario) {
        Usuario usuarioRecovered = this.repository.findOne(usuario.getId());
        usuarioRecovered.setNome(usuario.getNome());
        usuarioRecovered.setEmail(usuario.getEmail());
        //this.repository.updateNomeAndEmail(usuario.getNome(), usuario.getEmail(), usuario.getId());
        this.repository.save(usuarioRecovered);
    }

    @Transactional(readOnly = false)
    public void updateSenha(Usuario usuario) {
        Usuario usuarioRecovered = this.repository.findOne(usuario.getId());
        usuarioRecovered.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        //this.repository.updateSenha(usuarioRecovered.getSenha(), usuarioRecovered.getId());
        this.repository.save(usuarioRecovered);
    }

    @Transactional(readOnly = false)
    public void updatePerfil(Usuario usuario) {
        Usuario usuarioRecovered = this.repository.findOne(usuario.getId());
        usuarioRecovered.setPerfil(usuario.getPerfil());
        this.repository.save(usuarioRecovered);
    }
}