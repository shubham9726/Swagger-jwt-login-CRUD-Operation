package com.decipherzone.studmgmtmongodb.repository;

import com.decipherzone.studmgmtmongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long> {

}