package com.tikitaka.triptroop.block.dto.response;

import com.tikitaka.triptroop.block.domain.entity.Block;
import com.tikitaka.triptroop.block.domain.type.BlockStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BlockTableResponse {

    private final Long id;

    private final Long blockedId;

    private final Long blockerId;

    private final BlockStatus status;

    @CreatedDate
    private final LocalDateTime blockedAt;

    @LastModifiedDate
    private final LocalDateTime modifiedAt;

    private final LocalDateTime unblockedAt;

    public static BlockTableResponse from(final Block block) {
        return new BlockTableResponse(
                block.getId(),
                block.getBlockedId(),
                block.getBlockerId(),
                block.getStatus(),
                block.getBlockedAt(),
                block.getModifiedAt(),
                block.getUnblockedAt()
        );
    }

}
