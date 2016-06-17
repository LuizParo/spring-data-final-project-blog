package br.com.devmedia.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByDescricao(String descricao);
}