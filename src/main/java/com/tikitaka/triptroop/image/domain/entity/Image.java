package com.tikitaka.triptroop.image.domain.entity;

import com.tikitaka.triptroop.image.domain.type.ImageKind;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Image {

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
        updateIdBasedOnKind(kind, id);
        this.path = path;
        this.uuid = uuid;
        this.extension = extension;
        this.name = name;
    }

    public static Image of(ImageKind kind, Long id, String path, String uuid, String extension, String name) {
        return new Image(kind, id, path, uuid, extension, name);
    }

    private void updateIdBasedOnKind(ImageKind kind, Long id) {
        switch (kind) {
            case REPORT -> this.reportId = id;
            case TRAVEL -> this.travelId = id;
            case SCHEDULE -> this.scheduleId = id;
            case INQUIRY -> this.inquiryId = id;
            case COMPANION -> this.companionId = id;
        }
    }
}
