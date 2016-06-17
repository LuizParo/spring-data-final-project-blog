package br.com.devmedia.blog.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.repository.PostagemRepository;
import br.com.devmedia.blog.util.MyReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PostagemService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PostagemRepository repository;
    
    @Autowired
    private MyReplaceString linkFormatter;
    
    public Postagem findById(Long id) {
        return this.repository.findOne(id);
    }
    
    public Postagem findByPermalink(String permalink) {
        return this.repository.findByPermalink(permalink);
    }
    
    public List<Postagem> findAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.repository.delete(id);
    }
    
    @Transactional(readOnly = false)
    public void saveOrUpdate(Postagem postagem) {
        if(postagem.isNew()) {
            this.save(postagem);
        } else {
            this.update(postagem);
        }
    }
    
    private void save(Postagem postagem) {
        postagem.setPermalink(this.linkFormatter.formatPermalink(postagem.getTitulo()));
        postagem.setDataPostagem(LocalDateTime.now());
        this.repository.save(postagem);
    }
    
    private void update(Postagem postagem) {
        Postagem postagemMerged = this.repository.findOne(postagem.getId());
        
        if(!postagemMerged.getTitulo().equals(postagem.getTitulo())) {
            postagemMerged.setTitulo(postagem.getTitulo());
        }
        
        if(!postagemMerged.getTexto().equals(postagem.getTexto())) {
            postagemMerged.setTexto(postagem.getTexto());
        }
        
        this.repository.save(postagemMerged);
    }
}