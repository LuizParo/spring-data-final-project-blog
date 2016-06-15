package br.com.devmedia.blog.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Autor;
import br.com.devmedia.blog.repository.AutorRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AutorService implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
    
    @Transactional(readOnly = false)
    public void save(Autor autor) {
        if (autor.getId() == null) {
            this.repository.save(autor);
        } else {
            this.repository.updatenomeAndBiografia(autor.getNome(), autor.getBiografia(), autor.getId());
        }
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.repository.delete(id);
    }
}