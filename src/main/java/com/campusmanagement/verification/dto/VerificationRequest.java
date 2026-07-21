package com.campusmanagement.verification.dto;
import com.campusmanagement.verification.enums.RequestedRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationRequest {

    @NotNull
    private RequestedRole requestedRole;

    @NotBlank
    private String documentUrl;
}