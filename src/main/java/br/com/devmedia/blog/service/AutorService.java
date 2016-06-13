package br.com.devmedia.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Autor;
import br.com.devmedia.blog.repository.AutorRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AutorService {

    @Autowired
    private AutorRepository repository;
    
    public Autor findById(Long id) {
        return this.repository.findOne(id);
    }
    
    public Autor findByNome(String nome) {
        return this.repository.findByNome(nome);
    }
    
    public List<Autor> findAll() {
        return this.repository.findAll();
    }
    
    public void save(Autor autor) {
        this.repository.save(autor);
    }
}