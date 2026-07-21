package com.campusmanagement.student.entity;

import com.campusmanagement.student.enums.Department;
import com.campusmanagement.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private Department department;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false)
    private Integer admissionYear;

    @Builder.Default
    @Column(nullable = false)
    private Double cgpa = 0.0;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;
}