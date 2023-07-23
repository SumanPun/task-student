package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Entity.Subscription;
import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscription")
public class subscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping ("/{studentId}/SubscriptionPlan/{subscriptionPlanId}")
    public ResponseEntity<ApiResponse> addSubscription(@PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId) {

        ApiResponse response = this.subscriptionService.addSubscription(studentId,subscriptionPlanId);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
