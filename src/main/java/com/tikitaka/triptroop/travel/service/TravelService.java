package com.tikitaka.triptroop.travel.service;


import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.common.exception.ForbiddenException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepository;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepositoryImpl;
import com.tikitaka.triptroop.place.dto.response.PlaceTravelResponse;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepositoryImpl;
import com.tikitaka.triptroop.travel.dto.request.TravelRequest;
import com.tikitaka.triptroop.travel.dto.request.TravelUpdateRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelInfoResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelsResponse;
import com.tikitaka.triptroop.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final PlaceRepository placeRepository;
    private final PlaceRepositoryImpl placeRepositoryImpl;
    private final TravelRepositoryImpl travelRepositoryImpl;
    private final ProfileService profileService;
    private final TravelCommentService travelCommentService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    /* 페이징 처리 */
    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("id").descending());
    }

    /* 여행지 소개 조회 */
    @Transactional(readOnly = true)
    public Page<TravelsResponse> findAll(final Integer page, final Long categoryId, final Long areaId, final String title) {

        Page<Travel> travels = null;
        if (areaId != null && areaId > 0) {
            travels = travelRepository.findByAreaIdAndVisibility(getPageable(page), areaId, Visibility.PUBLIC);
        } else if (categoryId != null && categoryId > 0) {
            travels = travelRepository.findByCategoryIdAndVisibility(getPageable(page), categoryId, Visibility.PUBLIC);
        } else if (title != null && !title.isEmpty()) {
            travels = travelRepository.findByTitleAndVisibility(getPageable(page), title, Visibility.PUBLIC);
        } else {
            travels = travelRepository.findByVisibility(getPageable(page), Visibility.PUBLIC);
        }

        return travels.map(TravelsResponse::from);
    }

    /* 상세조회 (JPQL) */
    public TravelInfoResponse getTravelInfo(Long travelId) {

        Travel travels = travelRepository.findById(travelId).orElseThrow();
        TravelResponse travel = travelRepositoryImpl.findDetailedTravelByIdAndVisibility(travelId);
        List<ImageResponse> images = travelRepositoryImpl.findImagesByTravelId(travelId);
        PlaceTravelResponse place = placeRepositoryImpl.findPlaceById(travels.getPlaceId());
        return TravelInfoResponse.of(travel, images, place);
    }

    /* 여행지 소개 상세 조회*/
//    @Transactional(readOnly = true)
//    public TravelResponse findByTravelId(final Long id) {
//
//        Travel foundTravel = travelRepository.findByIdAndVisibility(id, Visibility.PUBLIC)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
//
//        Category foundCategory = categoryRepository.findById(foundTravel.getCategoryId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY));
//
//        Area foundArea = areaRepository.findById(foundTravel.getAreaId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_AREA));
//        Place foundPlace = placeRepository.findById(foundTravel.getPlaceId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PLACE));
//
//        List<TravelComment> travelComments = travelCommentRepository.findByTravelId(foundTravel.getId());
//
//
//        Travel travel = travelRepositoryImpl.findDetailedTravelByIdAndVisibility(id, visibility)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
//        Place places = placeRepository.findByIdAndAddressAndName(placeId, address, name)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_MAP));
//
//
//        return TravelResponse.from(foundTravel, foundCategory, foundArea, foundPlace, travelComments);
//    }


    /* 여행지 소개 등록 */
    public Long save(final TravelRequest travelRequest, final Long userId, final List<MultipartFile> images) {

        //        Category category = categoryRepository.findById(travelRequest.getCategoryId())
        //                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY_CODE));
        //        Area area = areaRepository.findById(travelRequest.getAreaId())
        //                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_AREA_CODE));
        //        Place place = placeRepository.findById(travelRequest.getPlaceId())
        //                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PLACE_CODE));

        final Travel newTravel = Travel.of(
                userId,
                travelRequest.getCategoryId(),
                travelRequest.getAreaId(),
                travelRequest.getPlaceId(),
                travelRequest.getTitle(),
                travelRequest.getContent()
        );

        final Travel travel = travelRepository.save(newTravel);
        imageService.saveAll(ImageKind.TRAVEL, travel.getId(), images);

        return travel.getId();
    }

    /* 여행소개 상세 조회 (된거) */
//    public TravelCommentUserResponse getTravelCommentUser(Long travelId) {
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
//        System.out.println("travel = " + travel);
//        Image image = imageRepository.findById(travelId).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_IMAGE));
//        System.out.println("image = " + image);
//        TravelComment travelComment = travelCommentRepository.findById(travel.getId()).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_COMMENT));
//        System.out.println("travelComment = " + travelComment);
//        User user = userRepository.findById(travel.getId()).orElseThrow();
//        System.out.println("user = " + user);
//        TravelCommentUserResponse travelCommentUserResponse = TravelCommentUserResponse.of(
//                travel.getTitle(),
//                travel.getContent(),
//                travelComment.getId(),
//                travelComment.getContent(),
//                user.getId(),
//                user.getEmail(),
//                user.getPassword(),
//                user.getName(),
//                image.getName(),
//                image.getPath()
//        );
//
//        return travelCommentUserResponse;
//    }

//    /* 여행 소개 상세 조회 (수정) */
//    public TravelDetailResponse findTravelDetail(Long travelId) {
//
//        Travel travel = travelRepository.findById(travelId)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
//        List<Image> images = imageRepository.findByTravelId(travelId);
//        List<ImageResponse> image = ImageResponse.from(images);
//        Place place = placeRepository.findById(travel.getPlaceId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PLACE));
//        UserProfileResponse userProfile = profileService.findUserProfileByUserId(travel.getUserId());
//        Page<TravelCommentResponse> travelComment = travelCommentService.findAll(1, travelId);
//
//        return TravelDetailResponse.of(
//                travel.getTitle(),
//                travel.getContent(),
//                PlaceResponse.from(place),
//                userProfile,
//                image,
//                travelComment
//        );
//    }


    /* 게시글 수정 */
    public void updateTravel(Long travelId, TravelUpdateRequest travelRequest, Long userId) {

        if (!travelRepository.existsByUserIdAndId(userId, travelId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }

        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));

        travel.update(
                travelRequest.getCategoryId(),
                travelRequest.getAreaId(),
                travelRequest.getPlaceId(),
                travelRequest.getTitle(),
                travelRequest.getContent(),
                Visibility.valueOf(travelRequest.getStatus())
        );


    }

    /* 게시글을 삭제합시다.♩♪*/
    public void deleteTravel(Long travelId, Long userId) {

        if (!travelRepository.existsByUserIdAndId(userId, travelId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }

        travelRepository.deleteById(travelId);
    }


    /* 공개상태 변경 */
    @Transactional
    public void updateStatus(Long userId, Long travelId, String status) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(() ->
                new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
        if (!travelRepository.existsByUserIdAndId(userId, travelId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }
        switch (status) {
            case "PUBLIC":
                travel.updateStatus(Visibility.PUBLIC);
                break;
            case "PRIVATE":
                travel.updateStatus(Visibility.PRIVATE);
                break;
        }

    }
}
