package com.example.crudapp.crudapp.Services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crudapp.crudapp.Entity.Role;
import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Exceptions.ResourceNotFoundException;
import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Payloads.StudentDto;
import com.example.crudapp.crudapp.Repository.RoleRepository;
import com.example.crudapp.crudapp.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public StudentDto createStudent(StudentDto studentDto) {
		
		Student student = this.dtoToStudent(studentDto);
		student.setActive(true);
		student.setAddedDate(new Date());
		student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
		
		Role role = this.roleRepository.findById(2).get();
		student.getRoles().add(role);
		
		Student saveStudent = this.studentRepository.save(student);
		
		return this.studentToDto(saveStudent);
		
	}
	
	public StudentDto getStudentById(int studentId) {
		
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student", "studentId", studentId));
		return this.studentToDto(student);
	}
	
	public List<StudentDto> getAllStudents() {
		
		List<Student> listStudents = this.studentRepository.findAll();
		List<StudentDto> listStudentDtos = listStudents.stream().map((student) -> this.studentToDto(student)).collect(Collectors.toList());
		
		return listStudentDtos;
	}
	
	public StudentDto updateStudent(int studentId, StudentDto studentDto) {
		
		Student student = this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student", "studentId", studentId));
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setAddress(studentDto.getAddress());
		student.setSemester(studentDto.getSemester());
		
		student.setAddedDate(new Date());
		this.studentRepository.save(student);
		
		return this.studentToDto(student);
	}

	
	public ApiResponse deleteStudent(int studentId) {
		
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student", "studentId", studentId));
		this.studentRepository.delete(student);
		
		return new ApiResponse("successfully deleted",true);
	}
	
	public StudentDto studentToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setEmail(student.getEmail());
		studentDto.setPassword(student.getPassword());
		studentDto.setSemester(student.getSemester());
		studentDto.setAddress(student.getAddress());
		studentDto.setActive(student.getActive());
		studentDto.setAddedDate(student.getAddedDate());
		return studentDto;
	}
	
	public Student dtoToStudent(StudentDto studentDto) {
		Student student = new Student();
		student.setId(studentDto.getId());
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setSemester(studentDto.getSemester());
		student.setAddress(studentDto.getAddress());
		student.setActive(studentDto.getActive());
		student.setAddedDate(studentDto.getAddedDate());
		return student;
	}
	
	public List<Student> getStudentList() {
		
		List<Student> students = this.studentRepository.findAll();
		return students;
	}
	
}
