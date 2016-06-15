package br.com.devmedia.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    public Postagem findByPermalink(String permalink);
}