package com.campusmanagement.lostfound.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalDecisionRequest {

    @NotBlank
    private String remarks;
}