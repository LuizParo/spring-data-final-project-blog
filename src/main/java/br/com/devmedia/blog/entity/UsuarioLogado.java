package br.com.devmedia.blog.entity;

import java.io.Serializable;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class UsuarioLogado extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    
    public UsuarioLogado(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), AuthorityUtils.createAuthorityList(usuario.getPerfil().toString()));
        this.usuario = usuario;
    }

    public Long getId() {
        return usuario.getId();
    }

    public Perfil getPerfil() {
        return usuario.getPerfil();
    }
}