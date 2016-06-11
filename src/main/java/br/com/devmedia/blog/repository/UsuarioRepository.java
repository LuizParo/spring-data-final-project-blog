package br.com.devmedia.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findByEmail(String email);
    
    public Usuario findByAvatar(Avatar avatar);

    @Modifying
    @Query("update Usuario u set u.nome = ?1, u.email = ?2 where u.id = ?3")
    public void updateNomeAndEmail(String nome, String email, Long id);
}