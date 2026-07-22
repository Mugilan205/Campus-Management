package com.campusmanagement.complaint.dto;

import com.campusmanagement.complaint.enums.ComplaintCategory;
import com.campusmanagement.complaint.enums.ComplaintPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

    @NotNull
    private ComplaintCategory category;

    private String attachmentUrl;

    @NotNull
    private ComplaintPriority priority ;
}