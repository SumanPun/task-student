package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Payloads.StudentDto;
import com.example.crudapp.crudapp.Services.FileService;
import com.example.crudapp.crudapp.Services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ImageUploadController {

    private final FileService fileService;
    private final StudentService studentService;

    @Value("${project.image}")
    private String path;

    public ImageUploadController(FileService fileService, StudentService studentService) {
        this.fileService = fileService;
        this.studentService = studentService;
    }
    @PostMapping("/student/upload/image/{imgId}")
    public ResponseEntity<StudentDto> uploadImage(
            @RequestParam("image")MultipartFile photo,
            @PathVariable Integer imgId
            ) throws IOException {
        StudentDto studentDto = this.studentService.getStudentById(imgId);
        String fileName = this.fileService.uploadImage(path, photo);
        studentDto.setImageName(fileName);
        StudentDto updateStudent = this.studentService.updateStudent(imgId, studentDto);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

}
