package com.tikitaka.triptroop.notice.domain.repository;

import com.tikitaka.triptroop.notification.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notification, Long> {

}
