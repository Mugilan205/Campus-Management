package com.campusmanagement.student.dto;

import com.campusmanagement.student.enums.Department;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;

    private Long userId;

    private String name;

    private String email;

    private String rollNumber;

    private Department department;

    private Integer semester;

    private Integer admissionYear;

    private Double cgpa;

    private String phoneNumber;

    private String address;
}