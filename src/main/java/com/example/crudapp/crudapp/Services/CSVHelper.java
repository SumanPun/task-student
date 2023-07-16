package com.example.crudapp.crudapp.Services;


import com.example.crudapp.crudapp.Payloads.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"id","name", "email", "password", "semester", "imageName", "address",
        "active", "addedDate"
    };

    public static boolean hasCSVFormat(MultipartFile file) {
        if(!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<StudentDto> csvToStudent(InputStream inputStream) {

        log.info("initialize import csv to database");

       //final CSVFormat csvFormat = CSVFormat.Builder.create().setHeader(HEADERs).setAllowMissingColumnNames(true).build();

        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
                        //csvFormat);
        ) {

            List<StudentDto> listOfStudent = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            String pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS";
            SimpleDateFormat dateFormat =  new SimpleDateFormat(pattern);

            for (CSVRecord csvRecord : csvRecords) {

                String dataStr = String.valueOf(csvRecord.get("addedDate"));
                Date date = dateFormat.parse(dataStr);
                StudentDto student = new StudentDto(
                        Integer.parseInt(csvRecord.get("id")),
                        csvRecord.get("name"),
                        csvRecord.get("email"),
                        csvRecord.get("password"),
                        csvRecord.get("semester"),
                        csvRecord.get("address"),
                        csvRecord.get("imageName"),
                        Boolean.parseBoolean(csvRecord.get("active")),
                        date

                );
                listOfStudent.add(student);
            }
            log.info("import csv to database completed");
            return listOfStudent;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file" + e.getMessage());
        }
    }
}
