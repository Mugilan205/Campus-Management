package com.campusmanagement.community.request.dto;


import com.campusmanagement.community.request.enums.JoinRequestDecision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityJoinRequestDecisionRequest {

    private JoinRequestDecision decision;
    private String remarks;

    public Boolean getApproved() {
        return JoinRequestDecision.APPROVE == decision;
    }
}