package com.campusmanagement.community.privatechat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivateMessageRequest {

    @NotBlank
    private String message;
}
