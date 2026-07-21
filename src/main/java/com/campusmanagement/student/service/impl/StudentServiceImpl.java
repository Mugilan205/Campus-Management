package com.campusmanagement.student.service.impl;

import com.campusmanagement.security.SecurityUtils;
import com.campusmanagement.student.dto.StudentProfileRequest;
import com.campusmanagement.student.dto.StudentProfileUpdateRequest;
import com.campusmanagement.student.dto.StudentResponse;
import com.campusmanagement.student.entity.Student;
import com.campusmanagement.student.mapper.StudentMapper;
import com.campusmanagement.student.repository.StudentRepository;
import com.campusmanagement.student.service.StudentService;
import com.campusmanagement.user.entity.User;
import com.campusmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createProfile(StudentProfileRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        if (studentRepository.findByUser(currentUser).isPresent()) {
            throw new RuntimeException("Student profile already exists.");
        }

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new RuntimeException("Roll number already exists.");
        }

        Student student = Student.builder()
                .user(currentUser)
                .rollNumber(request.getRollNumber())
                .department(request.getDepartment())
                .semester(request.getSemester())
                .admissionYear(request.getAdmissionYear())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();

        studentRepository.save(student);

        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse getMyProfile() {

        User currentUser = SecurityUtils.getCurrentUser();

        Student student = studentRepository.findByUser(currentUser)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found."));

        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse updateProfile(StudentProfileUpdateRequest request) {

        User currentUser = SecurityUtils.getCurrentUser();

        Student student = studentRepository.findByUser(currentUser)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found."));

        student.setPhoneNumber(request.getPhoneNumber());
        student.setAddress(request.getAddress());

        studentRepository.save(student);

        return studentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found."));

        return studentMapper.toResponse(student);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found."));

        studentRepository.delete(student);
    }
}