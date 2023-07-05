package com.example.crudapp.crudapp.Payloads;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	
	private int Id;
	private String name;
	private String email;
	private String semester;
	private String address;
	private Boolean active;
	private Date addedDate;

}
