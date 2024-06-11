package com.tikitaka.triptroop.schedule.domain.entity;


import com.tikitaka.triptroop.area.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.image.domain.entity.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE schedules SET is_deleted ='1',deleted_at = current_timestamp() WHERE schedule_id = ?")
public class Schedule extends BaseTimeEntity {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    private Integer count;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PUBLIC;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;

    private Integer views = 0;

    @OneToMany
    @JoinColumn(name = "schedule_Id")
    private List<Image> images;


    private Schedule(String title, Integer count, Long userId, Area area, LocalDate endDate, LocalDate startDate) {
        this.title = title;
        this.count = count;
        this.userId = userId;
        this.area = area;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public static Schedule of(String title, Integer count, Long userId, Area area, LocalDate endDate, LocalDate startDate) {
        return new Schedule(
                title, count, userId, area, endDate, startDate
        );
    }

    public void update(String title, Integer count, Area area, LocalDate endDate, LocalDate startDate) {
        this.title = title;
        this.count = count;
        this.area = area;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public void changeStatus(Visibility visibility) {
        this.visibility = visibility;
    }

}
