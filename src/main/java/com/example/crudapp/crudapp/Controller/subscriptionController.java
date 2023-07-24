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

    private final SubscriptionService subscriptionService;

    public subscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping ("/{studentId}/SubscriptionPlan/premium/{subscriptionPlanId}")
    public ResponseEntity<ApiResponse> addPremiumsubscription(@PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId) {

        ApiResponse response = this.subscriptionService.addPremiumSubscription(studentId,subscriptionPlanId);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping ("/{studentId}/SubscriptionPlan/free")
    public ResponseEntity<ApiResponse> addFreeSubscription(@PathVariable(name = "studentId") Integer studentId) {

        ApiResponse response = this.subscriptionService.takeFreeSubscription(studentId);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
