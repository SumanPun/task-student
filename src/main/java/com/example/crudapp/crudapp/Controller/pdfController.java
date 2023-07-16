package com.example.crudapp.crudapp.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Services.PdfGenerator;
import com.example.crudapp.crudapp.Services.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class pdfController {
		
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/api/v1/students/export-to-pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerkey = "Content-Disposition";
	    String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
	    List < Student > listofStudents = studentService.getStudentList();
	    PdfGenerator generator = new PdfGenerator();
	    generator.generate(listofStudents, response);
	}
	
}
