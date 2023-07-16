package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Payloads.EmailRequestDto;
import com.example.crudapp.crudapp.Services.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MailSenderController {

   private final MailService mailService;

   public MailSenderController(MailService mailService) {
       this.mailService = mailService;
   }

   @PostMapping("/send-mail")
   public ResponseEntity<String> sendMail(@RequestBody EmailRequestDto emailRequest) {

       Map<String, String > model = new HashMap<>();
       model.put("name", emailRequest.getName());
       model.put("value", "Welcome to John Doe Mail");
       String response = mailService.sendMail(emailRequest, model);
       return new ResponseEntity<>(response, HttpStatus.OK);

   }
}
