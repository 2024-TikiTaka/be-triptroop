package com.tikitaka.triptroop.block.controller;

import com.tikitaka.triptroop.block.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BlockController {

    private final BlockService blockService;

    /* 신고 목록 조회 */
    @GetMapping("/block")
    public ResponseEntity<String> getBlockTest(
            @RequestParam(required = false) final Long blockedId
    ) {
        return ResponseEntity.ok("Hello");
    }
}
