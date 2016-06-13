package br.com.devmedia.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.blog.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    public Autor findByNome(String nome);
}