package com.example.crudapp.crudapp.Payloads;

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

}
