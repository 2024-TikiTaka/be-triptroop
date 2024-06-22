package com.tikitaka.triptroop.image.service;

import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Profile("!prod")
@Service
@Transactional
@RequiredArgsConstructor
public class LocalStorageService implements ImageService {

    @Value("${image.image-url}")
    private String imageUrl;

    @Value("${image.image-dir}")
    private String imageDir;

    private final ImageRepository imageRepository;

    @Override
    public void save(ImageKind kind, Long targetId, MultipartFile image) {

        String filename = FileUploadUtils.uploadFile(imageDir, image);
        Image newImage = Image.of(
                kind,
                targetId,
                imageUrl,
                filename,
                image.getContentType(),
                image.getOriginalFilename()
        );

        imageRepository.save(newImage);
    }

    @Override
    public void saveAll(ImageKind kind, Long targetId, List<MultipartFile> images) {

        for (MultipartFile image : images) {
            save(kind, targetId, image);
        }
    }

    @Override
    public void update(ImageKind kind, Long targetId, MultipartFile image) {

        deleteAll(kind, targetId);
        save(kind, targetId, image);
    }

    @Override
    public void updateAll(ImageKind kind, Long targetId, List<MultipartFile> images) {

        deleteAll(kind, targetId);

        for (MultipartFile image : images) {
            save(kind, targetId, image);
        }
    }

    @Override
    public String uploadProfile(MultipartFile image) {

        return FileUploadUtils.uploadFile(imageDir, image);
    }

    @Override
    public void deleteProfile(String profileImage) {
        String filename = profileImage.replaceAll(imageUrl, "");
        FileUploadUtils.deleteFile(imageDir, filename);
    }

    private void deleteAll(ImageKind kind, Long targetId) {

        List<Image> images = findImagesByKindAndTargetId(kind, targetId);

        for (Image image : images) {
            String filename = image.getUuid();
            FileUploadUtils.deleteFile(imageDir, filename);
            imageRepository.delete(image);
        }
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
}
