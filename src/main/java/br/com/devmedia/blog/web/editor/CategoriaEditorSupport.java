package br.com.devmedia.blog.web.editor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import br.com.devmedia.blog.entity.Categoria;
import br.com.devmedia.blog.service.CategoriaService;

public class CategoriaEditorSupport extends CustomCollectionEditor {
    
    @Autowired
    private CategoriaService categoriaService;

    public CategoriaEditorSupport(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionType, CategoriaService categoriaService) {
        super(collectionType);
        this.categoriaService = categoriaService;
    }
    
    @Override
    protected Object convertElement(Object element) {
        Long id = Long.valueOf((String) element);
        Categoria categoria = this.categoriaService.findById(id);
        
        return super.convertElement(categoria);
    }
}