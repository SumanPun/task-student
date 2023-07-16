package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Services.CSVHelper;
import com.example.crudapp.crudapp.Services.CSVService;
import com.example.crudapp.crudapp.Services.CsvGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/students")
public class csvController {

    private final CsvGenerator csvGenerator;
    private final CSVService csvService;

    public csvController(CsvGenerator csvGenerator, CSVService csvService)
    {
        this.csvGenerator = csvGenerator;
        this.csvService = csvService;
    }

    @GetMapping("/export-to-csv")
    public void getAllStudentsInCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition","attachment; filename=\"students.csv\"");
        csvGenerator.writeStudentToCsv(response.getWriter());
    }

    @PostMapping("/uploadCSV")
    public ResponseEntity<ApiResponse> uploadCSV(@RequestParam ("file") MultipartFile file) {

        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                message = "uploaded file successfully" + file.getOriginalFilename();
                return  new ResponseEntity<>(new ApiResponse(message,true), HttpStatus.OK);
            } catch (Exception e) {
                message = "failed to upload file "+file.getOriginalFilename();
                return new ResponseEntity<>(new ApiResponse(message, false), HttpStatus.OK);
            }
        }
        message = "please upload CSV file";
        return  new ResponseEntity<>(new ApiResponse(message, false), HttpStatus.OK);
    }
}
