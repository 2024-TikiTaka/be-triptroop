package com.tikitaka.triptroop.image.util;

import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ImageUtils {

    public static List<String> extractImageInfo(List<ImageOriginalResponse> imageOriginalResponses) {
        return imageOriginalResponses.stream()
                .map(ImageOriginalResponse::getOriginalName)
                .collect(Collectors.toList());
    }

}
