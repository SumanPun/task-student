package com.example.crudapp.crudapp.Payloads;

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
	private String password;
	private String semester;
	private String dob;
	private String address;

	private String imageName;
	private Boolean active;
	private Date addedDate;

}
