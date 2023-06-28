package com.example.crudapp.crudapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Payloads.StudentDto;
import com.example.crudapp.crudapp.Services.StudentService;

@RestController
@RequestMapping("/api/v1/student/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/admin/")
	public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto) {
		
		StudentDto registerStudent = this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(registerStudent, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable ("id") int student_Id) {
		
		StudentDto studentDto = this.studentService.getStudentById(student_Id);
		return new ResponseEntity<>(studentDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<StudentDto>> getAllStudent() {
		
		List<StudentDto> studentDtos = this.studentService.getAllStudents();
		return new ResponseEntity<>(studentDtos, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable ("id") int studentId, @RequestBody StudentDto studentDto) {
		
		StudentDto studentUpdated = this.studentService.updateStudent(studentId, studentDto);
		return new ResponseEntity<>(studentUpdated, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable ("id") int studentId) {
		
		ApiResponse deletedStudent = this.studentService.deleteStudent(studentId);
		return new ResponseEntity<>(deletedStudent, HttpStatus.OK);
		
	}
	
	
	
	
	
	
}
