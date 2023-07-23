package com.example.crudapp.crudapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean freeTrial;
    private boolean subscribed;
    private Date startDate;
    private Date endDate;
    @OneToOne
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subscriptionPlanId",referencedColumnName = "id")
    private SubscriptionPlan subscriptionPlan;
}
