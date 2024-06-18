package com.tikitaka.triptroop.place.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "places")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    public Long id;

    public String kakaomapId;

    public String address;

    public String name;

    public LocalDateTime deletedAt;

    private Place(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public void update(Long id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }


    public static Place of(String address, String name) {
        return new Place(address, name);
    }
}
