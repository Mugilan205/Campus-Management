package com.campusmanagement.student.service;

import com.campusmanagement.student.dto.StudentProfileRequest;
import com.campusmanagement.student.dto.StudentProfileUpdateRequest;
import com.campusmanagement.student.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createProfile(StudentProfileRequest request);

    StudentResponse getMyProfile();

    StudentResponse updateProfile(StudentProfileUpdateRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    void deleteStudent(Long id);
}