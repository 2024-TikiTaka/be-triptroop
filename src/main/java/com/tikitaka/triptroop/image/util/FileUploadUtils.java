package com.tikitaka.triptroop.image.util;

import com.tikitaka.triptroop.common.exception.ServerInternalException;
import com.tikitaka.triptroop.image.domain.entity.Image;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static com.tikitaka.triptroop.common.exception.type.ExceptionCode.FAIL_TO_DELETE_FILE;
import static com.tikitaka.triptroop.common.exception.type.ExceptionCode.FAIL_TO_UPLOAD_FILE;


public class FileUploadUtils {

    public static String getFullPath(Image image) {
        return image.getPath() + image.getUuid();
    }

    /**
     * 랜덤 파일명 생성
     */
    public static String createFilename(String ext) {
        return UUID.randomUUID().toString() + "." + ext;
    }

    /**
     * 파일 업로드
     */
    public static String uploadFile(String uploadDir, MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            Path uploadPath = Paths.get(uploadDir);

            /* 업로드 경로가 존재하지 않을 시 경로 먼저 생성 */
            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);

            /* 파일명 생성 */
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String filename = createFilename(ext);

            /* 파일 저장 */
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException e) {
            throw new ServerInternalException(FAIL_TO_UPLOAD_FILE);
        }
    }

    /**
     * 파일 삭제
     */
    public static void deleteFile(String uploadDir, String fileName) {

        try {
            Path uploadPath = Paths.get(uploadDir);
            Path filePath = uploadPath.resolve(fileName);

            Files.delete(filePath);
        } catch (IOException e) {
            throw new ServerInternalException(FAIL_TO_DELETE_FILE);
        }
    }
}







