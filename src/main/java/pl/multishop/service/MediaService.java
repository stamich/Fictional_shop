package pl.multishop.service;

import pl.multishop.model.Media;

import java.util.List;

public interface MediaService {

    Media findById(String id);
    Media findByOriginalName(String originalName);
    void saveMedia(Media media);
    void updateMedia(Media media);
    void deleteById(String id);
    List<Media> findAll();
}
