package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminInquiryRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminInquiryReplyRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminInquiryDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminInquiryListResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminInquiryService {

    private final AdminInquiryRepository admininquiryRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;

    /* 1. 문의 목록 조회 */
    @Transactional(readOnly = true)
    public Page<AdminInquiryListResponse> getInquiryList(final Integer page, final String type, final String keyword, final String sort) {

        String sortBy = (sort == null || sort.isEmpty()) ? "id" : sort;
        Pageable pageable = getPageable(page, sortBy);

        boolean hasKeyword = keyword != null && !keyword.isEmpty();
        String jpql = buildJpqlQuery(type, hasKeyword, sortBy);
        String countJpql = buildCountJpqlQuery(type, hasKeyword);

        Query query = entityManager.createQuery(jpql, AdminInquiryListResponse.class);
        Query countQuery = entityManager.createQuery(countJpql);

        if (hasKeyword) {
            query.setParameter("keyword", "%" + keyword + "%");
            countQuery.setParameter("keyword", "%" + keyword + "%");
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AdminInquiryListResponse> resultList = query.getResultList();
        long total = (long) countQuery.getSingleResult();

        return new PageImpl<>(resultList, pageable, total);
    }

    /* 2. 문의 상세 조회 */
    @Transactional(readOnly = true)
    public AdminInquiryDetailResponse getInquiryDetail(Long inquiryId) {
        Inquiry inquiry = admininquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_INQUIRY));

        User user = userRepository.findById(inquiry.getUserId())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        Profile profile = profileRepository.findById(inquiry.getUserId())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));

        List<Image> images = imageRepository.findByInquiryId(inquiryId);
        List<ImageOriginalResponse> imageOriginalResponses = ImageOriginalResponse.from(images);

        return AdminInquiryDetailResponse.from(inquiry, user, imageOriginalResponses, profile);
    }

    /* 3. 답변 등록 */
    @Transactional
    public AdminInquiryReplyRequest inquiryReplySave(final Long inquiryId, final AdminInquiryReplyRequest adminInquiryReplyRequest, List<MultipartFile> images) {

        Inquiry inquiry = admininquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_INQUIRY));

        inquiry.addReply(adminInquiryReplyRequest.getReply());
        admininquiryRepository.save(inquiry);

        return AdminInquiryReplyRequest.of(adminInquiryReplyRequest.getReply());
    }

    //---- 메서드 분리 ----//
    // 페이징 관련 메서드 분리
    private Pageable getPageable(final Integer page, final String sort) {
        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
    }

    private String buildJpqlQuery(String type, boolean hasKeyword, String sortBy) {
        StringBuilder jpql = new StringBuilder("SELECT i, u, p ");
        jpql.append("FROM Inquiry i LEFT JOIN User u ON i.userId = u.id LEFT JOIN Profile p ON u.id = p.userId ");

        if (hasKeyword) {
            appendWhereClause(jpql, type);
        }

        jpql.append(" ORDER BY u.").append(sortBy);
        return jpql.toString();
    }

    private String buildCountJpqlQuery(String type, boolean hasKeyword) {
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(i) ");
        countJpql.append("FROM Inquiry i LEFT JOIN User u ON i.userId = u.id LEFT JOIN Profile p ON u.id = p.userId ");

        if (hasKeyword) {
            appendWhereClause(countJpql, type);
        }

        return countJpql.toString();
    }

    private void appendWhereClause(StringBuilder jpql, String type) {
        if (type != null && !type.isEmpty()) {
            String columnName = getColumnName(type);
            jpql.append("WHERE ").append(columnName).append(" LIKE :keyword ");
        } else {
            List<String> columns = new ArrayList<>();
            columns.add("u.email LIKE :keyword");
            columns.add("p.nickname LIKE :keyword");
            columns.add("str(i.inquiryKind) LIKE :keyword");
            columns.add("i.content LIKE :keyword");
            columns.add("str(i.status) LIKE :keyword");
            jpql.append("WHERE ").append(String.join(" OR ", columns)).append(" ");
        }
    }

    private String getColumnName(String type) {
        switch (type) {
            case "email":
                return "u.email";
            case "nickname":
                return "p.nickname";
            case "inquiryKind":
                return "str(i.inquiryKind)";
            case "content":
                return "i.content";
            case "status":
                return "str(i.status)";
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    // 회원 등록 메서드 분리
//    private void checkForDuplicates(AdminUserSaveRequest adminUserSaveRequest) {
//        userService.checkEmailDuplicate(adminUserSaveRequest.getEmail());
//        profileService.checkNicknameDuplicate(adminUserSaveRequest.getNickname());
//    }
}
