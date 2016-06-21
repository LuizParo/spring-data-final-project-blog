package br.com.devmedia.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Categoria;
import br.com.devmedia.blog.repository.CategoriaRepository;
import br.com.devmedia.blog.util.MyReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    
    @Autowired
    private MyReplaceString linkFormatter;
    
    @Transactional(readOnly = false)
    public void saveOrUpdate(Categoria categoria) {
        categoria.setPermalink(this.linkFormatter.formatPermalink(categoria.getDescricao()));
        this.repository.save(categoria);
    }
    
    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.repository.delete(id);
    }
    
    public Categoria findById(Long id) {
        return this.repository.findOne(id);
    }
    
    public Categoria findByDescricao(String descricao) {
        return this.repository.findByDescricao(descricao);
    }
    
    public List<Categoria> findAll() {
        Sort sort = new Sort(new Order(Direction.ASC, "descricao"));
        return this.repository.findAll(sort);
    }
}