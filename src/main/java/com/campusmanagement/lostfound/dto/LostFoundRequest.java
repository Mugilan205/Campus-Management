package com.campusmanagement.lostfound.dto;

import com.campusmanagement.lostfound.enums.ItemCategory;
import com.campusmanagement.lostfound.enums.LostFoundType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LostFoundRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LostFoundType type;

    @NotNull
    private ItemCategory category;

    private String attachmentUrl;

    @NotBlank
    private String location;
}