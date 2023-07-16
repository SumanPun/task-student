package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Payloads.StudentDto;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CSVService {

    private final StudentRepository studentRepository;

    public CSVService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public void save(MultipartFile file) {

        log.info("logging to save");

        try {
            List<StudentDto> studentDtos = CSVHelper.csvToStudent(file.getInputStream());
            List<Student> students = studentDtos.stream().map((s)-> this.dtoToStudent(s)).collect(Collectors.toList());
            log.info("before save");
            studentRepository.saveAll(students);
            log.info("After save");
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map((s)-> this.studentToDto(s)).collect(Collectors.toList());
        return studentDtos;
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
}
