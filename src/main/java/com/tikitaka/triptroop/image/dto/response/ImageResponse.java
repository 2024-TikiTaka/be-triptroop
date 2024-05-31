package com.tikitaka.triptroop.image.dto.response;

import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageResponse {
    private final Long imageId;
    private final String fullPath;

    public static ImageResponse from(Image image) {
        return new ImageResponse(
                image.getId(),
                FileUploadUtils.getFullPath(image)
        );
    }

}
