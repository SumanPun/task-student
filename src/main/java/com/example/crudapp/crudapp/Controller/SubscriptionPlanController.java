package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Entity.SubscriptionPlan;
import com.example.crudapp.crudapp.Services.SubscriptionPlanService;
import com.example.crudapp.crudapp.Services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptionPlan")
public class SubscriptionPlanController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubscriptionPlan> createSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan) {

        SubscriptionPlan subscriptionCreate = this.subscriptionPlanService.createSubscription(subscriptionPlan);
        return new ResponseEntity<>(subscriptionCreate, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionPlan>> getSubscriptionOption() {
        List<SubscriptionPlan> subscriptionPlans = this.subscriptionPlanService.getSubscriptionOptions();
        return new ResponseEntity<>(subscriptionPlans,HttpStatus.OK);
    }
}
