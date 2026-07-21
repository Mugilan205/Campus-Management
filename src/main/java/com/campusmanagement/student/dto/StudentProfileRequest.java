package com.campusmanagement.student.dto;

import com.campusmanagement.student.enums.Department;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileRequest {

    @NotBlank
    private String rollNumber;

    @NotNull
    private Department department;

    @Min(1)
    @Max(12)
    private Integer semester;

    @Min(2000)
    private Integer admissionYear;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;
}