package br.com.devmedia.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "autores")
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Length(min = 3, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String nome;
    
    @NotBlank(message = "Este campo não aceita valor em branco.")
    @Length(min = 5, max = 255, message = "Este campo aceita entre {min} e {max} caracteres.")
    @Column(nullable = false, length = 255)
    private String biografia;
    
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "autor")
    private List<Postagem> postagens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Postagem> getPostagens() {
        return postagens;
    }
    
    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
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
        Autor other = (Autor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nome=" + nome + ", biografia=" + biografia + ", usuario=" + usuario + "]";
    }
}