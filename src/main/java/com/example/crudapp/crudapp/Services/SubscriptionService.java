package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.Student;
import com.example.crudapp.crudapp.Entity.Subscription;
import com.example.crudapp.crudapp.Entity.SubscriptionPlan;
import com.example.crudapp.crudapp.Exceptions.ResourceNotFoundException;
import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Repository.StudentRepository;
import com.example.crudapp.crudapp.Repository.SubscriptionPlanRepository;
import com.example.crudapp.crudapp.Repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final StudentRepository studentRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, StudentRepository studentRepository, SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.studentRepository = studentRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public ApiResponse takeFreeSubscription(Integer studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("user","userId",studentId));
        Subscription freeSubscription = student.getSubscription();
        if(freeSubscription == null) {
            Subscription subscription = new Subscription();
            subscription.setFreeTrial(true);
            subscription.setSubscribed(false);
            subscription.setStudent(student);
            student.setSubscription(subscription);
            LocalDateTime date = LocalDateTime.now();
            subscription.setStartDate(date);
            subscription.setEndDate(date.plusDays(3));
            this.subscriptionRepository.save(subscription);
            this.studentRepository.save(student);
        }else  {
            return new ApiResponse("You have already taken free trails",false);
        }
        return new ApiResponse("Free Subscription added to "+studentId,true);
    }


    public ApiResponse addPremiumSubscription(Integer studentId, Integer subscriptionPlanId) {

        SubscriptionPlan subscriptionPlan = this.subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(()-> new ResourceNotFoundException("subscriptionPlan","subscriptionPlanId",subscriptionPlanId));
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("user","userId",studentId));
        Subscription oldSubscription = student.getSubscription();
        if(payment()) {
            if(oldSubscription == null) {
                Subscription subscription = new Subscription();
                subscription.setFreeTrial(true);
                subscription.setSubscribed(true);
                subscription.setStudent(student);
                student.setSubscription(subscription);
                LocalDateTime date = LocalDateTime.now();
                subscription.setStartDate(date);
                subscription.setEndDate(date.plusDays(subscriptionPlan.getValidDay()));
                subscription.setSubscriptionPlan(subscriptionPlan);
                this.subscriptionRepository.save(subscription);
                this.studentRepository.save(student);
            }
            else {
                LocalDateTime date = LocalDateTime.now();
                if(!checkValidDate(oldSubscription.getEndDate())) {
                    oldSubscription.setEndDate(date.plusDays(subscriptionPlan.getValidDay()));
                }else {
                    int remainingDay = compareDates(date,oldSubscription.getEndDate());
                    oldSubscription.setEndDate(date.plusDays(subscriptionPlan.getValidDay() + remainingDay));
                }
                oldSubscription.setStartDate(LocalDateTime.now());
                oldSubscription.setSubscriptionPlan(subscriptionPlan);
                this.subscriptionRepository.save(oldSubscription);
            }
            return new ApiResponse("Subscription added to "+ oldSubscription.getSubscriptionPlan().getValidDay() + " days",true);
        }
        else {
            return new ApiResponse("Please payment first",false);
        }
    }

    public int compareDates(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        int difference = dateTime1.compareTo(dateTime2);
        return difference;
    }


    public Boolean payment() {
        return true;
    }

    public boolean checkValidDate(LocalDateTime endDate) {
        LocalDateTime currentDate = LocalDateTime.now();
        int datesCompares = currentDate.compareTo(endDate);
        if(datesCompares >= 0) {
            return true;
        }else {
            return false;
        }
    }
}
