package br.com.devmedia.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    public Postagem findByPermalink(String permalink);

    public List<Postagem> findByCategoriasPermalink(String link);

    public List<Postagem> findByAutorNome(String nome);
    
    public Page<Postagem> findAllByOrderByDataPostagemDesc(Pageable pageable);

    public Page<Postagem> findAllByCategoriasPermalinkOrderByDataPostagemDesc(Pageable pageable, String permalink);

    public Page<Postagem> findAllByAutorIdOrderByDataPostagemDesc(Pageable pageable, Long id);
}