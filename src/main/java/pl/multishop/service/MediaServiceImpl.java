package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.MediaDao;
import pl.multishop.model.Media;

import java.util.List;

@Transactional
@Service("mediaService")
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaDao dao;

    @Override
    public Media findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Media findByOriginalName(String originalName) {
        return dao.findByOriginalName(originalName);
    }

    @Override
    public void saveMedia(Media media) {
        dao.saveMedia(media);
    }

    @Override
    public void updateMedia(Media media) {
        Media entity = dao.findByOriginalName(media.getOriginalName());
        if(entity != null){
            entity.setMimeType(media.getMimeType());
            entity.setOriginalName(media.getOriginalName());
            entity.setMedia(media.getMedia());
            entity.setProducts(media.getProducts());
        }
    }

    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<Media> findAll() {
        return dao.findAll();
    }
}
