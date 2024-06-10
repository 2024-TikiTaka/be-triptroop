package com.tikitaka.triptroop.notification.domain.repository;

import com.tikitaka.triptroop.notification.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
