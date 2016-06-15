package br.com.devmedia.blog.service;

import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.repository.AvatarRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvatarService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(AvatarService.class);
    
    @Autowired
    private AvatarRepository repository;
    
    @Transactional(readOnly = false)
    public void saveOrUpdate(Avatar avatar) {
        this.repository.save(avatar);
    }
    
    public Avatar getAvatarByUpload(MultipartFile file) {
        Avatar avatar = new Avatar();
        if(file != null && !file.isEmpty()) {
            try {
                avatar.setTitulo(file.getOriginalFilename());
                avatar.setTipo(file.getContentType());
                avatar.setAvatar(file.getBytes());
            } catch (IOException e) {
                LOG.error("Ocorreu um erro em AvatarService: " + e.getMessage());
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return avatar;
    }

    public Avatar findById(Long id) {
        return this.repository.findOne(id);
    }
}