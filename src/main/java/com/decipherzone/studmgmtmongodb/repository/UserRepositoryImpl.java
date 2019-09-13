package com.decipherzone.studmgmtmongodb.repository;

import com.decipherzone.studmgmtmongodb.model.Student;
import com.decipherzone.studmgmtmongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public UserRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;

  }

  @Override
  public User saveUser(User user) {
    return mongoTemplate.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("email").is(email)), User.class);
  }

  @Override
  public boolean checkEmailPresent(String email) {
    return mongoTemplate.exists(new Query().addCriteria(Criteria.where("email").is(email)), User.class);
  }

  @Override
  public Student saveStudent(Student student) {
    return mongoTemplate.save(student);
  }

  @Override
  public Student findId(int Id) {
    return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(Id)), Student.class);
  }

  @Override
  public Collection<Student> findAllStudent() {
    return mongoTemplate.find(new Query().addCriteria(Criteria.where("deleted").is(false)), Student.class);
  }

  @Override
  public boolean checkNamePresent(String name) {
    return mongoTemplate.exists(new Query().addCriteria(Criteria.where("name").is(name)), Student.class);
  }

  @Override
  public boolean checkIdPresent(int Id) {
    return mongoTemplate.exists(new Query().addCriteria(Criteria.where("_id").is(Id)), Student.class);
  }

  @Override
  public <S extends User> S save(S s) {
    return null;
  }

  @Override
  public <S extends User> List<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<User> findById(Integer integer) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Integer integer) {
    return false;
  }

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public Iterable<User> findAllById(Iterable<Integer> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Integer integer) {

  }

  @Override
  public void delete(User user) {

  }

  @Override
  public void deleteAll(Iterable<? extends User> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<User> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<User> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends User> S insert(S s) {
    return null;
  }

  @Override
  public <S extends User> List<S> insert(Iterable<S> iterable) {
    return null;
  }

  @Override
  public <S extends User> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends User> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends User> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends User> boolean exists(Example<S> example) {
    return false;
  }
}