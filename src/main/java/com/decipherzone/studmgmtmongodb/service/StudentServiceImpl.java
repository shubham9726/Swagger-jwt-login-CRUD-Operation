package com.decipherzone.studmgmtmongodb.service;

import com.decipherzone.studmgmtmongodb.model.Student;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface StudentServiceImpl {
  Student addStudent(Student student);

  Student deleteStudent(int Id);

  Class<? extends StudentService> getStudentById(int Id);

  ResponseEntity saveStudentDetails(Student student);

  AggregationResults gettotalFee();

  Collection<Student> getAllStudents();
}
