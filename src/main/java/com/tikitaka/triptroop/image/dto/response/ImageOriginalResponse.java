package com.tikitaka.triptroop.image.dto.response;

import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ImageOriginalResponse {

    private final String fullPath;
    private final String originalName;

    public static ImageOriginalResponse from(Image image) {
        return new ImageOriginalResponse(
                FileUploadUtils.getFullPath(image),
                image.getOriginalName()
        );
    }

    public static List<ImageOriginalResponse> from(List<Image> images) {
        return images.stream()
                .map(ImageOriginalResponse::from)
                .collect(Collectors.toList());
    }

}
