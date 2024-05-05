package com.greatlearning.sms.service;

import com.greatlearning.sms.entity.Student;

import java.util.List;

public interface StudentService {

	List<Student> list();
	
	public void save(Student student);

	public Student findById(Long studentId);

	public void deleteById(Long studentId);
}
