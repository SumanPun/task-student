package com.example.crudapp.crudapp.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	public ResourceNotFoundException(String resouceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resouceName, fieldName, fieldValue));
		this.resourceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
