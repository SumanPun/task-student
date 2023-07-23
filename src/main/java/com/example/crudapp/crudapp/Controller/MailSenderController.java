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

       String logoURL = "https://ausnepit.com.au/wp-content/uploads/2021/09/ausnepwhite.png";
       String pdfAttach = "https://drive.google.com/file/d/1kq2TZCBtB0yZodubpsnxE_Ayl0nGo7cm/view?usp=drive_link";

       Map<String, String > model = new HashMap<>();
       model.put("name", emailRequest.getName());
       model.put("logoURL", logoURL);
       model.put("message", emailRequest.getMessage());
       model.put("from", emailRequest.getFrom());
       model.put("attachPdf",pdfAttach);
       String response = mailService.sendMail(emailRequest, model);
       return new ResponseEntity<>(response, HttpStatus.OK);

   }
}
