package com.tikitaka.triptroop.image.domain.entity;

import com.tikitaka.triptroop.schedule.domain.type.ImageKind;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "images") // <- ""안에 엔티티 매핑할 테이블 명 적어주세요.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Image { // <- Entity 를 본인의 엔티티 명으로 바꿔 주세요. ( ! 첫문자 대문자임 (ex-Member O / member X) )

    /* 매핑할 필드( 컬럼명 : 카멜케이스 )를 적어주세요. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long travelId;
    private Long scheduleId;
    private Long companionId;
    private Long reportId;
    private Long inquiryId;
    @Enumerated(EnumType.STRING)
    private ImageKind kind;
    private String path;
    private String uuid;
    private String name;
    private String extension;
    @CreatedDate
    private LocalDateTime createdAt;

    private Image(ImageKind kind, Long id, String path, String uuid, String extension, String name) {
        this.kind = kind;
        setIdByKind(kind, id);
        this.path = path;
        this.uuid = uuid;
        this.extension = extension;
        this.name = name;
    }

    public static Image of(ImageKind kind, Long id, String path, String uuid, String extension, String name) {
        return new Image(kind, id, path, uuid, extension, name);
    }

    private void setIdByKind(ImageKind kind, Long id) {
        switch (kind) {
            case REPORT -> this.reportId = id;
            case TRAVEL -> this.travelId = id;
            case SCHEDULE -> this.scheduleId = id;
            case INQUIRY -> this.inquiryId = id;
            case COMPANION -> this.companionId = id;
        }
    }
}
