package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminNoticeRepository;
import com.tikitaka.triptroop.notice.domain.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminNoticeService {

    private final AdminNoticeRepository adminNoticeRepository;

    public List<Notice> getNoticeList() {
        return adminNoticeRepository.findAll();
    }
}
