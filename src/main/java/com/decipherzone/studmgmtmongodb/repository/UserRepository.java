package com.decipherzone.studmgmtmongodb.repository;

import com.decipherzone.studmgmtmongodb.model.Student;
import com.decipherzone.studmgmtmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface UserRepository extends MongoRepository<User, Integer> {

  User saveUser(User user);

  User findByEmail(String email);

  boolean checkEmailPresent(String email);

  boolean checkNamePresent(String name);

  boolean checkIdPresent(int Id);

  Student saveStudent(Student student);

  Student findId(int Id);

  Collection<Student> findAllStudent();
}