package com.example.crudapp.crudapp.config;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Payloads.EmailRequestDto;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import com.example.crudapp.crudapp.Services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("myScheduledTask")
@Slf4j
public class ScheduledTask {

    @Autowired
    private MailService mailService;
    @Autowired
    private StudentRepository studentRepository;

    private static final LocalDateTime currentTimeSchedular = LocalDateTime.now();

    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime() {
        log.info("The Time is "+currentTimeSchedular);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void sendBirthDAYMail() {
        List<Student> lists = this.studentRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Student> sendMailList = lists.stream().filter((student)-> LocalDate.parse(student.getDob(), formatter).getDayOfYear() == currentTimeSchedular.getDayOfYear()).collect(Collectors.toList());
        for(Student student: sendMailList) {
            String logoURL = "https://ausnepit.com.au/wp-content/uploads/2021/09/ausnepwhite.png";
            String from = "ABCCompany";
            String subject = "Birthday Wishes";
            Map<String, String > model = new HashMap<>();
            model.put("logoURL", logoURL);
            model.put("from", from);
            model.put("name", student.getName());
            String message = "Many Many Happy Returns of the day "+ student.getName();
            model.put("message", message);
            EmailRequestDto dto = new EmailRequestDto();
            dto.setFrom(from);
            dto.setTo(student.getEmail());
            dto.setMessage(message);
            dto.setName(student.getName());
            dto.setSubject(subject);
            String response = this.mailService.sendBirthdayMail(dto, model);
            log.info(response);
        }
    }
}


