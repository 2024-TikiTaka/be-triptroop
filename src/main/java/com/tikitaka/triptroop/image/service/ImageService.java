package com.tikitaka.triptroop.image.service;

import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    @Value("${image.image-url}")
    private String imageUrl;

    @Value("${image.image-dir}")
    private String imageDir;

    private final ImageRepository imageRepository;

    @Transactional
    public void save(ImageKind kind, Long targetId, MultipartFile image) {

        String uploadedFileName = FileUploadUtils.uploadFile(imageDir, image);
        Image newImage = Image.of(kind, targetId, imageUrl, uploadedFileName, image.getContentType(), image.getOriginalFilename());
        imageRepository.save(newImage);
    }

    @Transactional
    public void saveAll(ImageKind kind, Long targetId, List<MultipartFile> images) {
        for (MultipartFile image : images) {
            save(kind, targetId, image);
        }
    }

    @Transactional
    public void updateImage(ImageKind kind, Long targetId, MultipartFile image) {
        deleteExistingImages(kind, targetId);
        save(kind, targetId, image);
    }

    private List<Image> findImagesByKindAndTargetId(ImageKind kind, Long targetId) {
        return switch (kind) {
            case TRAVEL -> imageRepository.findByTravelId(targetId);
            case SCHEDULE -> imageRepository.findByScheduleId(targetId);
            case COMPANION -> imageRepository.findByCompanionId(targetId);
            case REPORT -> imageRepository.findByReportId(targetId);
            case INQUIRY -> imageRepository.findByInquiryId(targetId);
            case NOTICE -> imageRepository.findByNoticeId(targetId);
        };
    }

    private void deleteExistingImages(ImageKind kind, Long targetId) {
        List<Image> existingImages = findImagesByKindAndTargetId(kind, targetId);
        for (Image existingImage : existingImages) {
            String existingFilename = existingImage.getUuid();
            FileUploadUtils.deleteFile(imageDir, existingFilename);
            imageRepository.delete(existingImage);
        }
    }

    /* 다솔 추가 */
    @Transactional
    public void updateAll(ImageKind kind, Long targetId, List<MultipartFile> images) {

        deleteExistingImages(kind, targetId);
        for (MultipartFile image : images) {
            save(kind, targetId, image);
        }
    }

    //존재 여부 체크
    public boolean hasValidImages(List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            return false;
        }

        for (MultipartFile image : images) {
            if (image == null || image.isEmpty() ||
                    image.getOriginalFilename() == null || image.getOriginalFilename().isEmpty() ||
                    image.getContentType() == null || image.getContentType().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}




