package com.tikitaka.triptroop.category.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        return new Category(name);
    }

    public void updateCategory(String name) {
        this.name = name;
        this.deletedAt = LocalDateTime.now();
    }

//    public void deleteCategory() {
//        this.deletedAt = LocalDateTime.now();
//    }
}
