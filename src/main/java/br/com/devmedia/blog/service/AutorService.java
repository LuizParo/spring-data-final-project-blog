package br.com.devmedia.blog.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    public Page<Autor> findByPagination(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return this.repository.findAllByOrderByNomeAsc(pageable);
    }
    
    @Transactional(readOnly = false)
    public void save(Autor autor) {
        if (autor.isNew()) {
            this.repository.save(autor);
        } else {
            this.repository.updateNomeAndBiografia(autor.getNome(), autor.getBiografia(), autor.getId());
        }
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.repository.delete(id);
    }

    public Autor findByUsuario(Long id) {
        return this.repository.findByUsuarioId(id);
    }
}