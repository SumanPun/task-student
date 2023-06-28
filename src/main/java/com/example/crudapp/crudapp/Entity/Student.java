package com.example.crudapp.crudapp.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student")
public class Student {
	
	@Id
	private int Id;
	private String name;
	private String email;
	private String semester;
	private String address;
}
