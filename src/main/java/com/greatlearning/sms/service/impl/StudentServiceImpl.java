package com.greatlearning.sms.service.impl;

import com.greatlearning.sms.entity.Student;
import com.greatlearning.sms.repository.StudentRepository;
import com.greatlearning.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> list() {

        return studentRepository.findAll();
    }


    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }


    @Override
    public Student findById(Long ticketId) {
        return studentRepository.findById(ticketId).get();
    }


    @Override
    public void deleteById(Long ticketId) {
        studentRepository.deleteById(ticketId);
    }

}