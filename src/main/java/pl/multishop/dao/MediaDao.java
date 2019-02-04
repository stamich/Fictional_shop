package pl.multishop.dao;

import pl.multishop.model.Media;

import java.util.List;

public interface MediaDao {

    Media findById(String id);
    Media findByOriginalName(String originalName);
    void saveMedia(Media media);
    void deleteById(String id);
    List<Media> findAll();
}
