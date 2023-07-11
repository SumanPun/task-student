package com.example.crudapp.crudapp.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class StudentService implements StudentServiceInterface<StudentDto> {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public StudentDto createStudent(StudentDto studentDto) {


		Student student = this.dtoToStudent(studentDto);
		student.setActive(true);
		student.setImageName("Default.png");
		student.setAddedDate(new Date());
		student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
		
		Role role = this.roleRepository.findById(2).get();
		student.getRoles().add(role);
		
		Student saveStudent = this.studentRepository.save(student);
		
		return this.studentToDto(saveStudent);
		
	}

	@Override
	public StudentDto getStudentById(int studentId) {
		
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student", "studentId", studentId));
		return this.studentToDto(student);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		log.info("Fetching student's all data");
		List<Student> listStudents = this.studentRepository.findAll();
		List<StudentDto> listStudentDtos = listStudents.stream().map((student) -> this.studentToDto(student)).collect(Collectors.toList());
		
		return listStudentDtos;
	}

	@Override
	public StudentDto updateStudent(int studentId, StudentDto studentDto) {
		
		Student student = this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student", "studentId", studentId));
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setAddress(studentDto.getAddress());
		student.setSemester(studentDto.getSemester());
		student.setImageName(studentDto.getImageName());
		
		student.setAddedDate(new Date());
		this.studentRepository.save(student);
		
		return this.studentToDto(student);
	}

	@Override
	public ApiResponse deleteStudent(int studentId) {
		
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student", "studentId", studentId));
		this.studentRepository.delete(student);
		
		return new ApiResponse("successfully deleted",true);
	}

	@Override
	public List<StudentDto> findAll(boolean isDeleted) {
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedStudentFilter");
		filter.setParameter("isDeleted", isDeleted);
		List<Student> students = studentRepository.findAll();
		session.disableFilter("deletedStudentFilter");
		List<StudentDto> studentDtos = students.stream().map((s)-> this.studentToDto(s)).collect(Collectors.toList());
		return  studentDtos;
	}

	public StudentDto studentToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setEmail(student.getEmail());
		studentDto.setPassword(student.getPassword());
		studentDto.setSemester(student.getSemester());
		studentDto.setAddress(student.getAddress());
		studentDto.setImageName(student.getImageName());
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
		student.setImageName(studentDto.getImageName());
		student.setActive(studentDto.getActive());
		student.setAddedDate(studentDto.getAddedDate());
		return student;
	}

	public List<Student> getStudentList() {
		
		List<Student> students = this.studentRepository.findAll();
		return students;
	}
	
}
