package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Payloads.StudentDto;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class BirthdayService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllBirthdays() {
        List<Student> list = new ArrayList<>();
        Date date = new Date();
        // Define the input and output date formats
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the input date string to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(date.toString(), inputFormatter);

        // Format the LocalDateTime to the desired output format
        String formattedDate = dateTime.format(outputFormatter);

        List<Student> getStudents = this.studentRepository.findAll();
        for(Student student: getStudents) {
            if(student.getDob().equals(formattedDate)) {
                list.add(student);
            }
        }
        return list;
    }

    public void sendBirthdayMessage() {
        List<Student> students = getAllBirthdays();
        for (Student student : students) {
            String recipientEmail = student.getEmail();
            String message = "Happy Birthday, " + student.getName() + "!";
            sendEmail(recipientEmail, "Birthday Greetings", message);
        }
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }
}
