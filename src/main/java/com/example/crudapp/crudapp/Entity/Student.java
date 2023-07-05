package com.example.crudapp.crudapp.Entity;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String name;
	private String email;
	private String semester;
	private String address;
	private Boolean active;
	@Column(name = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
	private Date addedDate;
}
