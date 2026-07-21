package com.campusmanagement.student.controller;

import com.campusmanagement.student.dto.StudentProfileRequest;
import com.campusmanagement.student.dto.StudentProfileUpdateRequest;
import com.campusmanagement.student.dto.StudentResponse;
import com.campusmanagement.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentResponse> createProfile(
            @Valid @RequestBody StudentProfileRequest request) {

        return ResponseEntity.ok(studentService.createProfile(request));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentResponse> getMyProfile() {

        return ResponseEntity.ok(studentService.getMyProfile());
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentResponse> updateProfile(
            @Valid @RequestBody StudentProfileUpdateRequest request) {

        return ResponseEntity.ok(studentService.updateProfile(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}