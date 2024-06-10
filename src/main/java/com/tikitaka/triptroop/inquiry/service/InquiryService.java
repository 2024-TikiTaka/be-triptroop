package com.tikitaka.triptroop.inquiry.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminInquiryRepository;
import com.tikitaka.triptroop.inquiry.dto.response.InquiryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService {

    private final AdminInquiryRepository inquiryRepository;

    @Transactional(readOnly = true)
    public List<InquiryListResponse> getInquriryList() {
        return inquiryRepository.findAdminInquirysAll();
    }


}
