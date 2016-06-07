package br.com.devmedia.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findByEmail(String email);
    
    public Usuario findByAvatar(Avatar avatar);
}