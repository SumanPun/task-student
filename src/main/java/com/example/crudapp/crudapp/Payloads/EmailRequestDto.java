package com.example.crudapp.crudapp.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {

    private String from;
    private String to;
    private String subject;
    private String name;
    private String message;
}
