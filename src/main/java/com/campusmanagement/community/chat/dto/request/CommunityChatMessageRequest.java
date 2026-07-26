package com.campusmanagement.community.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityChatMessageRequest {

    @NotBlank
    private String message;

}
