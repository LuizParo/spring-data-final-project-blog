package br.com.devmedia.blog.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "postagem")
public class Postagem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 60)
    private String titulo;
    
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String texto;
    
    @Column(nullable = false, unique = true, length = 60)
    private String permalink;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(name = "data_postagem", nullable = false)
    private LocalDateTime dataPostagem;
    
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "postagem_categoria",
            joinColumns = @JoinColumn(name = "id_postagem"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias = new ArrayList<>();
    
    @OneToMany(mappedBy = "postagem", fetch = FetchType.EAGER)
    private List<Comentario> comentarios = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDateTime dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public List<Comentario> getComentarios() {
        Collections.sort(comentarios);
        return comentarios;
    }
    
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    public boolean isNew() {
        return null == this.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Postagem other = (Postagem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Postagem [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", permaLink=" + permalink
                + ", dataPostagem=" + dataPostagem + ", autor=" + autor + "]";
    }
}