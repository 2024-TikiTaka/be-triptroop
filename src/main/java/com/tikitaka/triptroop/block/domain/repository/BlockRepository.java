package com.tikitaka.triptroop.block.domain.repository;

import com.tikitaka.triptroop.block.domain.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlockRepository extends JpaRepository<Block, Long> {


}
