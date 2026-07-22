package com.campusmanagement.lostfound.service;

import com.campusmanagement.lostfound.dto.ApprovalDecisionRequest;
import com.campusmanagement.lostfound.dto.ClaimRequest;
import com.campusmanagement.lostfound.dto.LostFoundRequest;
import com.campusmanagement.lostfound.dto.LostFoundResponse;
import com.campusmanagement.lostfound.enitity.LostFound;
import com.campusmanagement.lostfound.enums.LostFoundStatus;
import com.campusmanagement.lostfound.mapper.LostFoundMapper;
import com.campusmanagement.lostfound.repository.LostFoundRepository;
import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LostFoundServiceImpl implements LostFoundService {

    private final LostFoundRepository lostFoundRepository;

    private final LostFoundMapper lostFoundMapper;

    @Override
    public LostFoundResponse createPost(LostFoundRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        LostFound lostFound = LostFound.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .type(request.getType())
                .category(request.getCategory())
                .attachmentUrl(request.getAttachmentUrl())
                .location(request.getLocation())
                .createdBy(currentUser)
                .status(LostFoundStatus.PENDING)
                .build();

        LostFound savedPost = lostFoundRepository.save(lostFound);

        return lostFoundMapper.toResponse(savedPost);
    }

    @Override
    public List<LostFoundResponse> getMyPosts() {

        User currentUser = SecurityUtils.getCurrentUser();

        return lostFoundRepository.findByCreatedBy(currentUser)
                .stream()
                .map(lostFoundMapper::toResponse)
                .toList();
    }

    @Override
    public List<LostFoundResponse> getActivePosts() {

        return lostFoundRepository
                .findAllByStatus(LostFoundStatus.ACTIVE)
                .stream()
                .map(lostFoundMapper::toResponse)
                .toList();
    }

    @Override
    public LostFoundResponse submitClaim(
            Long id,
            ClaimRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (post.getStatus() != LostFoundStatus.ACTIVE) {
            throw new RuntimeException(
                    "Item is not available for claiming.");
        }

        if (post.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new RuntimeException(
                    "You cannot claim your own item.");
        }

        post.setClaimedBy(currentUser);
        post.setClaimMessage(request.getClaimMessage());
        post.setClaimedAt(LocalDateTime.now());
        post.setStatus(LostFoundStatus.CLAIM_REQUESTED);

        return lostFoundMapper.toResponse(
                lostFoundRepository.save(post));
    }

    @Override
    public LostFoundResponse acceptClaim(Long id) {

        User currentUser = SecurityUtils.getCurrentUser();

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (!post.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new RuntimeException(
                    "Only the owner can accept a claim.");
        }

        if (post.getStatus() != LostFoundStatus.CLAIM_REQUESTED) {
            throw new RuntimeException(
                    "No claim request available.");
        }

        post.setStatus(LostFoundStatus.CLAIMED);

        return lostFoundMapper.toResponse(
                lostFoundRepository.save(post));
    }

    @Override
    public LostFoundResponse rejectClaim(
            Long id,
            ApprovalDecisionRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (!post.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new RuntimeException(
                    "Only the owner can reject a claim.");
        }

        if (post.getStatus() != LostFoundStatus.CLAIM_REQUESTED) {
            throw new RuntimeException(
                    "No claim request available.");
        }

        post.setStatus(LostFoundStatus.ACTIVE);

        post.setClaimedBy(null);
        post.setClaimMessage(null);
        post.setClaimedAt(null);

        post.setRemarks(request.getRemarks());

        return lostFoundMapper.toResponse(
                lostFoundRepository.save(post));
    }

    @Override
    public LostFoundResponse confirmReturn(Long id) {

        User currentUser = SecurityUtils.getCurrentUser();

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (!post.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new RuntimeException(
                    "Only the owner can confirm return.");
        }

        if (post.getStatus() != LostFoundStatus.CLAIMED) {
            throw new RuntimeException(
                    "Claim has not been accepted.");
        }

        post.setStatus(LostFoundStatus.RETURNED);
        post.setReturnedAt(LocalDateTime.now());

        return lostFoundMapper.toResponse(
                lostFoundRepository.save(post));
    }



    @Override
    public List<LostFoundResponse> getPendingPosts() {

        return lostFoundRepository
                .findAllByStatus(LostFoundStatus.PENDING)
                .stream()
                .map(lostFoundMapper::toResponse)
                .toList();
    }

    @Override
    public LostFoundResponse approvePost(
            Long id,
            ApprovalDecisionRequest request) {

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (post.getStatus() != LostFoundStatus.PENDING) {
            throw new RuntimeException("Post already reviewed.");
        }

        post.setStatus(LostFoundStatus.ACTIVE);
        post.setApprovedBy(SecurityUtils.getCurrentUser());
        post.setApprovedAt(LocalDateTime.now());
        post.setRemarks(request.getRemarks());

        LostFound saved = lostFoundRepository.save(post);

        return lostFoundMapper.toResponse(saved);
    }

    @Override
    public LostFoundResponse rejectPost(
            Long id,
            ApprovalDecisionRequest request) {

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (post.getStatus() != LostFoundStatus.PENDING) {
            throw new RuntimeException("Post already reviewed.");
        }

        post.setStatus(LostFoundStatus.REJECTED);
        post.setApprovedBy(SecurityUtils.getCurrentUser());
        post.setApprovedAt(LocalDateTime.now());
        post.setRemarks(request.getRemarks());

        LostFound saved = lostFoundRepository.save(post);

        return lostFoundMapper.toResponse(saved);
    }

    @Override
    public LostFoundResponse claimItem(
            Long id,
            ClaimRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        LostFound post = lostFoundRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));

        if (post.getStatus() != LostFoundStatus.ACTIVE) {
            throw new RuntimeException(
                    "Item is not available for claiming.");
        }

        if (post.getCreatedBy().getId().equals(currentUser.getId())) {
            throw new RuntimeException(
                    "You cannot claim your own post.");
        }

        if (post.getClaimedBy() != null) {
            throw new RuntimeException(
                    "Item has already been claimed.");
        }

        post.setClaimedBy(currentUser);
        post.setClaimMessage(request.getClaimMessage());
        post.setClaimedAt(LocalDateTime.now());
        post.setStatus(LostFoundStatus.CLAIMED);

        LostFound saved = lostFoundRepository.save(post);

        return lostFoundMapper.toResponse(saved);
    }



}