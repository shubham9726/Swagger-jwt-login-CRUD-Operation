package com.decipherzone.studmgmtmongodb.controller;

import com.decipherzone.studmgmtmongodb.controller.response.StudentResponse;
import com.decipherzone.studmgmtmongodb.model.Student;
import com.decipherzone.studmgmtmongodb.service.StudentService;
import com.decipherzone.studmgmtmongodb.service.StudentServiceImpl;
import com.decipherzone.studmgmtmongodb.service.UserService;
import com.decipherzone.studmgmtmongodb.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DemoController {

  private StudentServiceImpl studentServiceimpl;
  private ModelMapper modelMapper;

  @Autowired
  private DemoController(StudentServiceImpl studentServiceimpl, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.studentServiceimpl = studentServiceimpl;
  }

  @PostMapping("/addstudent")
  @ApiOperation(value = "Add Student")
  public Student addStudent(@ApiParam("add student") @RequestBody Student student) {
    return studentServiceimpl.addStudent(modelMapper.map(student, Student.class));
  }

  @ApiOperation(value = "Delete a student")
  @DeleteMapping(value = "/delete/{Id}")
  public Student delete(@PathVariable int Id) {
    return studentServiceimpl.deleteStudent(Id);
  }

  @ApiOperation(value = "Update a Student")
  @PutMapping(value = "/update/{Id}")
  public ResponseEntity updateStudent(@PathVariable int Id, @RequestBody Student student) {
    Class<? extends StudentService> storedStudent = studentServiceimpl.getStudentById(Id);
    student.setId(storedStudent.getModifiers());
    return studentServiceimpl.saveStudentDetails(student);
  }

  @GetMapping(value = "/getall")
  @ApiOperation(value = "View Student Record", response = StudentResponse.class)
  public Collection<Student> getAll() {
    return studentServiceimpl.getAllStudents();
  }

  @GetMapping(value = "/gettotalfee")
  @ApiOperation(value = "Total Fees")
  public AggregationResults gettotalfee() {
    return studentServiceimpl.gettotalFee();
  }
}