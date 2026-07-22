package com.campusmanagement.complaint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintDecisionRequest {

    @NotBlank
    private String remarks;
}
