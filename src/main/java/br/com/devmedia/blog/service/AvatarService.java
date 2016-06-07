package br.com.devmedia.blog.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.repository.AvatarRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvatarService {
    
    @Autowired
    private AvatarRepository repository;
    
    @Transactional(readOnly = false)
    public void saveOrUpdate(Avatar avatar) {
        this.repository.save(avatar);
    }
    
    public Avatar getAvatarByUpload(MultipartFile file) {
        Avatar avatar = new Avatar();
        if(file != null && file.isEmpty()) {
            try {
                avatar.setTitulo(file.getOriginalFilename());
                avatar.setTipo(file.getContentType());
                avatar.setAvatar(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return avatar;
    }
}