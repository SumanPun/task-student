package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Entity.Subscription;
import com.example.crudapp.crudapp.Entity.SubscriptionPlan;
import com.example.crudapp.crudapp.Exceptions.ResourceNotFoundException;
import com.example.crudapp.crudapp.Exceptions.SubscriptionException;
import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import com.example.crudapp.crudapp.Repository.SubscriptionPlanRepository;
import com.example.crudapp.crudapp.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private StudentRepository studentRepository;
    public ApiResponse addSubscription(Integer studentId, Integer subscriptionPlanId) {

        SubscriptionPlan subscriptionPlan = this.subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(()-> new ResourceNotFoundException("subscriptionPlan","subscriptionPlanId",subscriptionPlanId));
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("user","userId",studentId));
        Subscription oldSubscription = student.getSubscription();
        if(payment()) {
            if(oldSubscription == null) {
                Subscription subscription = new Subscription();
                subscription.setFreeTrial(true);
                if(subscriptionPlanId == 1) {
                    subscription.setSubscribed(false);
                }else {
                    subscription.setSubscribed(true);
                }
                subscription.setStudent(student);
                student.setSubscription(subscription);
                subscription.setStartDate(new Date());
                Date date = new Date();
                date.setDate(date.getDate() + subscriptionPlan.getValidDay());
                subscription.setEndDate(date);
                subscription.setSubscriptionPlan(subscriptionPlan);
                this.subscriptionRepository.save(subscription);
                this.studentRepository.save(student);
                oldSubscription = subscription;
            }
            else if (oldSubscription.isFreeTrial() && !oldSubscription.isSubscribed() && subscriptionPlanId.equals(1)) {

                return new ApiResponse("You have already taken free trails. ", false);
            }
            else {
                Date date = new Date();
                if(!checkValidDate(oldSubscription.getEndDate())) {
                    date.setDate(date.getDate() + subscriptionPlan.getValidDay());
                }else {
                    long differenceInMilliseconds = oldSubscription.getEndDate().getTime() - oldSubscription.getStartDate().getTime();
                    int daysDifference = Math.toIntExact(Long.valueOf(Math.abs(differenceInMilliseconds / (24 * 60 * 60 * 1000))));
                    date.setDate(date.getDate() + daysDifference + subscriptionPlan.getValidDay());
                }
                oldSubscription.setStartDate(new Date());
                oldSubscription.setEndDate(date);
                oldSubscription.setSubscriptionPlan(subscriptionPlan);
                this.subscriptionRepository.save(oldSubscription);
            }
            return new ApiResponse("Subscription added to "+ oldSubscription.getSubscriptionPlan().getValidDay() + " days",true);
        }
        else {
            return new ApiResponse("Please payment first",false);
        }
    }

//    public ApiResponse extendSubscription() {
//
//    }


    public Boolean payment() {
        return true;
    }

    public boolean checkValidDate(Date endDate) {
        Date currentDate = new Date();
        int datesCompares = currentDate.compareTo(endDate);
        if(datesCompares >= 0) {
            return true;
        }else {
            return false;
        }
    }
}
