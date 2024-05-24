package com.tikitaka.triptroop.image.service;

import com.tikitaka.triptroop.common.util.FileUploadUtils;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.schedule.domain.type.ImageKind;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private String getRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void save(ImageKind kind, Long id, MultipartFile image) {
        String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, getRandomName(), image);

        System.out.println(id + "@@@@@@@@");
        final Image newImage = Image.of(
                kind,
                id,
                IMAGE_URL,
                replaceFileName,
                image.getContentType(),
                image.getName()
        );
        imageRepository.save(newImage);
    }


}
