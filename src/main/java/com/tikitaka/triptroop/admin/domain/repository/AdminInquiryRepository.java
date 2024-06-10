package com.tikitaka.triptroop.admin.domain.repository;

import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import com.tikitaka.triptroop.inquiry.dto.response.InquiryListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminInquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("SELECT new com.tikitaka.triptroop.inquiry.dto.response.InquiryListResponse(i, u, p)" +
            "FROM Inquiry i LEFT JOIN User u ON i.userId = u.id " +
            "LEFT JOIN Profile p ON u.id = p.userId ")
    List<InquiryListResponse> findAdminInquirysAll();
}
