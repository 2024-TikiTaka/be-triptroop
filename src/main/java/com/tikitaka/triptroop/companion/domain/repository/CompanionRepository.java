package com.tikitaka.triptroop.companion.domain.repository;

import com.tikitaka.triptroop.companion.domain.entity.Companion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanionRepository extends JpaRepository<Companion, Long> {

}
