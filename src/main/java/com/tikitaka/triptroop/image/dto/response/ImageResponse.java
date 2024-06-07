package com.tikitaka.triptroop.image.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
public class ImageResponse {

    private final Long imageId;

    private final String fullPath;

    @QueryProjection
    public ImageResponse(Long imageId, String path, String uuid) {
        this.imageId = imageId;
        this.fullPath = path + uuid;
    }

    public static ImageResponse from(Image image) {
        return new ImageResponse(
                image.getId(),
                FileUploadUtils.getFullPath(image));
    }

    public static List<ImageResponse> from(List<Image> image) {
        return image.stream()
                .map(ImageResponse::from)
                .collect(Collectors.toList());
    }

    /* 다솔 추가 메서드 */
    @JsonIgnore
    public String getName() {
        String[] parts = fullPath.split("/");
        String fileName = parts[parts.length - 1];
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    @JsonIgnore
    public String getExtension() {
        String[] parts = fullPath.split("/");
        String fileName = parts[parts.length - 1];
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
