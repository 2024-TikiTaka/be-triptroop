package com.tikitaka.triptroop.image.service;


import com.tikitaka.triptroop.common.exception.ServerInternalException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;

@Profile("prod")
@Service
@Transactional
@RequiredArgsConstructor
public class S3StorageService implements ImageService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${image.image-url}")
    private String imageUrl;

    private final S3Client s3Client;

    private final ImageRepository imageRepository;

    @Override
    public void save(ImageKind kind, Long targetId, MultipartFile image) {

        try {
            String filename = createKey(image);

            s3Client.putObject(
                    PutObjectRequest.builder()
                                    .bucket(bucketName)
                                    .key(filename)
                                    .build(),
                    RequestBody.fromBytes(image.getBytes())
            );

            Image newImage = Image.of(
                    kind,
                    targetId,
                    imageUrl,
                    filename,
                    image.getContentType(),
                    image.getOriginalFilename()
            );

            imageRepository.save(newImage);

        } catch (Exception e) {
            throw new ServerInternalException(ExceptionCode.FAIL_TO_UPLOAD_FILE);
        }
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

        try {
            String key = createKey(image);

            s3Client.putObject(
                    PutObjectRequest.builder()
                                    .bucket(bucketName)
                                    .key(key)
                                    .build(),
                    RequestBody.fromBytes(image.getBytes())
            );

            return key;

        } catch (Exception e) {
            throw new ServerInternalException(ExceptionCode.FAIL_TO_UPLOAD_FILE);
        }
    }

    @Override
    public void deleteProfile(String profileImage) {
        String filename = profileImage.replaceAll(imageUrl, "");
        s3Client.deleteObject(o -> o.bucket(bucketName).key(filename));
    }

    private void deleteAll(ImageKind kind, Long targetId) {

        List<Image> images = findImagesByKindAndTargetId(kind, targetId);

        for (Image image : images) {
            String filename = image.getUuid();
            s3Client.deleteObject(o -> o.bucket(bucketName).key(filename));
            imageRepository.delete(image);
        }
    }

    private String createKey(MultipartFile file) {

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        return FileUploadUtils.createFilename(ext);
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
