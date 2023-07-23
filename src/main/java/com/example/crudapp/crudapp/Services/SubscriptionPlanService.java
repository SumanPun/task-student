package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.SubscriptionPlan;
import com.example.crudapp.crudapp.Repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlan createSubscription(SubscriptionPlan subscriptionPlan) {
        SubscriptionPlan subscriptionPlan1 = this.subscriptionPlanRepository.save(subscriptionPlan);
        return subscriptionPlan1;
    }

    public List<SubscriptionPlan> getSubscriptionOptions() {
        List<SubscriptionPlan> options = this.subscriptionPlanRepository.findAll();
        return options;
    }
}
