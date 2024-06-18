package com.tikitaka.triptroop.admin.domain.repository;


import com.tikitaka.triptroop.notice.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminNoticeRepository extends JpaRepository<Notice, Long> {
}
