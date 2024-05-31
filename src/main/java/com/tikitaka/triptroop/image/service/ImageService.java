package com.tikitaka.triptroop.image.service;

import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
        String replaceFileName = FileUploadUtils.uploadFile(IMAGE_DIR, getRandomName(), image);

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

    public void updateImage(ImageKind kind, Long id, MultipartFile image) {

        // 기존 이미지 찾기
        List<Image> existingImages = findImagesByKindAndId(kind, id);

        // 기존 이미지 삭제
        for (Image existingImage : existingImages) {
            String existingFileName = existingImage.getUuid();
            FileUploadUtils.deleteFile(IMAGE_DIR, existingFileName);
            System.out.println("파일 삭제에 실패했습니다: " + existingFileName);
            imageRepository.delete(existingImage);
        }


        // 새 이미지 업로드
        String replaceFileName = FileUploadUtils.uploadFile(IMAGE_DIR, getRandomName(), image);

        // 새 이미지 정보 저장
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

    private List<Image> findImagesByKindAndId(ImageKind kind, Long id) {
        return switch (kind) {
            case TRAVEL -> imageRepository.findByTravelId(id);
            case SCHEDULE -> imageRepository.findByScheduleId(id);
            case COMPANION -> imageRepository.findByCompanionId(id);
            case REPORT -> imageRepository.findByReportId(id);
            case INQUIRY -> imageRepository.findByInquiryId(id);
        };
    }
}



