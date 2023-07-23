package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CsvGenerator {

    private final StudentRepository studentRepository;

    public CsvGenerator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    public void writeStudentToCsv(Writer writer) {

        List<Student> students = studentRepository.findAll();

        try {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            csvPrinter.printRecord("Id","name","email","password","semester","dob","address", "imageName","active","addedDate");
            for(Student student : students) {
                csvPrinter.printRecord(student.getId(), student.getName(), student.getEmail(),student.getPassword(),
                        student.getSemester(),student.getDob(), student.getAddress(),  student.getImageName(), student.getActive(), student.getAddedDate());
            }
        } catch (IOException ex) {
            log.error("Error While Writing CSV" + ex);
        }
    }

}
