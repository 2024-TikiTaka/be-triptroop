package com.tikitaka.triptroop.image.service;

import com.tikitaka.triptroop.image.domain.type.ImageKind;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    void save(ImageKind kind, Long targetId, MultipartFile image);

    void saveAll(ImageKind kind, Long targetId, List<MultipartFile> images);

    void update(ImageKind kind, Long targetId, MultipartFile image);

    void updateAll(ImageKind kind, Long targetId, List<MultipartFile> images);

    String uploadProfile(MultipartFile image);

    void deleteProfile(String profileImage);
}
