package com.example.crudapp.crudapp.Payloads;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromoCodeDto {

    private int id;
    private String code;
    private int subscriptionId;
    private double flatDiscount;
    private double discountPercentage;
    private Date expirationDATE;
}
