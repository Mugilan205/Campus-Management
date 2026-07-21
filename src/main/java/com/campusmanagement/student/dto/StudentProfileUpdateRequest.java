package com.campusmanagement.student.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileUpdateRequest {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;
}
//can update only personal info