package com.example.crudapp.crudapp.Services;
import com.example.crudapp.crudapp.Payloads.ApiResponse;

import java.text.ParseException;
import java.util.List;

public interface StudentServiceInterface<T> {

    T createStudent(T entity);

    T getStudentById(int id);

    List<T> getAllStudents();

    T updateStudent(int id, T entity);
    ApiResponse deleteStudent(int id);
    List<T> findAll(boolean isDelted);


}
