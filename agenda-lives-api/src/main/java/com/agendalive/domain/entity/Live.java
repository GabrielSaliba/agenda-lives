package com.agendalive.domain.entity;

import com.agendalive.domain.enums.LiveStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "lives")
@Entity
public class Live {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime creationDate;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updateDate;

    @Column(nullable = false, length = 150)
    String name;

    @Column(nullable = false, length = 50)
    String channel;

    @Column(nullable = false, length = 500)
    String liveUrl;

    @Column(nullable = false, length = 50)
    LocalDateTime startDate;

    @Column(length = 50)
    LocalDateTime endDate;

    @Column(nullable = false)
    LiveStatus status;
}
