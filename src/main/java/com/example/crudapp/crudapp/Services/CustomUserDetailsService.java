package com.example.crudapp.crudapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Exceptions.ResourceNotFoundException;
import com.example.crudapp.crudapp.Repository.StudentRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student student = this.studentRepository.findByName(username).orElseThrow(()-> new ResourceNotFoundException("username", "" + username, 0) );
		return student;
	}

}
