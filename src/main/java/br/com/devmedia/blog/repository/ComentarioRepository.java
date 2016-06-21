package br.com.devmedia.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}