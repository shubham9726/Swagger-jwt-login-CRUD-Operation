package com.decipherzone.studmgmtmongodb.service;

import com.decipherzone.studmgmtmongodb.controller.request.StudentRequest;
import com.decipherzone.studmgmtmongodb.exception.StudentMngmtexception;
import com.decipherzone.studmgmtmongodb.model.Student;
import com.decipherzone.studmgmtmongodb.repository.StudentRepository;
import com.decipherzone.studmgmtmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

/**
 * Student Service
 */
@Service
public class StudentService implements StudentServiceImpl {

  private UserRepository userRepository;
  private StudentRepository studentRepository;
  private final MongoTemplate mongoTemplate;

  @Autowired
  public StudentService(UserRepository userRepository, StudentRepository studentRepository, MongoTemplate mongoTemplate) {
    this.userRepository = userRepository;
    this.studentRepository = studentRepository;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Student addStudent(Student student) {
    if (userRepository.checkNamePresent(student.getName())) {
      throw new StudentMngmtexception("Name already exist", HttpStatus.ALREADY_REPORTED);
    }
    return userRepository.saveStudent(student);
  }

  @Override
  public Student deleteStudent(int Id) {
    Student student = userRepository.findId(Id);
    student.setDeleted(Boolean.TRUE);
    return studentRepository.save(student);
  }

  @Override
  public Class<? extends StudentService> getStudentById(int Id) {
    if (userRepository.checkIdPresent(Id)) {
      return getClass();
    }
    return null;
  }

  @Override
  public ResponseEntity saveStudentDetails(Student student) {
    studentRepository.save(student);
    return null;
  }

  @Override
  public AggregationResults gettotalFee() {
    Aggregation aggregation = newAggregation(
      group().sum("fee").as("total"),
      project("total")
    );
    AggregationResults results = mongoTemplate.aggregate(aggregation, "student", StudentRequest.class);
    return results;
  }

  @Override
  public Collection<Student> getAllStudents() {
    return userRepository.findAllStudent();
  }
}
