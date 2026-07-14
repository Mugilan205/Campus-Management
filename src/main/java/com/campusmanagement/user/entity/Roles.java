package com.campusmanagement.user.entity;
import com.campusmanagement.common.entity.BaseEntity;
import com.campusmanagement.common.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column( name = "name", nullable = false, unique = true)
    private RoleType name;
    @Column(length = 255)
    private String description;
}