package br.com.devmedia.blog.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.entity.UsuarioLogado;

@Service
@Transactional(readOnly = true)
public class UsuarioLogadoDetailService implements Serializable, UserDetailsService {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogadoDetailService.class);

    @Autowired
    private UsuarioService service;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario user = this.service.findByEmail(username);
            LOGGER.info("User '" + username + "' found!");
            return new UsuarioLogado(user);
        } catch (Exception e) {
            LOGGER.error("User '" + username + "' not found!", e);
            throw new UsernameNotFoundException("User '" + username + "' not found!", e);
        }
    }
}