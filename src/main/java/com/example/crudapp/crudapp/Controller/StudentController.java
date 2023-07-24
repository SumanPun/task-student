package com.example.crudapp.crudapp.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Payloads.StudentDto;
import com.example.crudapp.crudapp.Services.StudentService;

@RestController
@RequestMapping("/api/v1/student/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto) {
		
		StudentDto registerStudent = this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(registerStudent, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{student_id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable ("student_id") int student_Id) {
		
		StudentDto studentDto = this.studentService.getStudentById(student_Id);
		return new ResponseEntity<>(studentDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<StudentDto>> getAllStudent() {
		
		List<StudentDto> studentDtos = this.studentService.getAllStudents();
		return new ResponseEntity<>(studentDtos, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable ("id") int studentId, @RequestBody StudentDto studentDto) {
		
		StudentDto studentUpdated = this.studentService.updateStudent(studentId, studentDto);
		return new ResponseEntity<>(studentUpdated, HttpStatus.OK);
		
	}

	@GetMapping("/findFilter")
	public ResponseEntity<List<StudentDto>> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
		List<StudentDto> findFilter = this.studentService.findAll(isDeleted);
		return new ResponseEntity<>(findFilter,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable ("id") int studentId) {
		
		ApiResponse deletedStudent = this.studentService.deleteStudent(studentId);
		return new ResponseEntity<>(deletedStudent, HttpStatus.OK);
		
	}
	
	
	
	
	
	
}
