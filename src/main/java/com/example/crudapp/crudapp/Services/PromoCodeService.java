package com.example.crudapp.crudapp.Services;

import com.example.crudapp.crudapp.Entity.PromoCode;
import com.example.crudapp.crudapp.Entity.SubscriptionPlan;
import com.example.crudapp.crudapp.Exceptions.ResourceNotFoundException;
import com.example.crudapp.crudapp.Payloads.ApiResponse;
import com.example.crudapp.crudapp.Payloads.PromoCodeDto;
import com.example.crudapp.crudapp.Repository.PromoCodeRepository;
import com.example.crudapp.crudapp.Repository.SubscriptionPlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private ModelMapper modelMapper;


    private String generateRandomCode() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();
        String generateCode = random.ints(leftLimit,rightLimit+1)
                .limit(targetStringLength)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
        return generateCode;
    }

    public PromoCodeDto createPromoCode(PromoCodeDto promoCode) {
        Date d=new Date();
        d.setDate((d.getDate())+2);
        System.out.println(d);
        PromoCode promoCode1 = this.modelMapper.map(promoCode,PromoCode.class);
        promoCode1.setCode(generateRandomCode());
        promoCode1.setExpirationDATE(d);
        this.promoCodeRepository.save(promoCode1);
        return this.modelMapper.map(promoCode1,PromoCodeDto.class);
    }

    public boolean promoCodeValid(String code) {
        boolean valid=false;
        PromoCode promoCode = this.promoCodeRepository.findByCode(code).orElseThrow(()-> new ResourceNotFoundException("code","codeName: "+ code,0));
        Date d = new Date();
        int compareDates = promoCode.getExpirationDATE().compareTo(d);

        if(compareDates > 0 || compareDates == 0) {
            ///promo not expired
            valid = true;
        }else if(compareDates < 0) {
            //promo code expired
            valid = false;
        }
        return valid;
    }

    public Object applyPromoCode(String code) {
        PromoCode promoCode = this.promoCodeRepository.findByCode(code).orElseThrow(()-> new ResourceNotFoundException("code","codeName: "+ code,0));
        if(promoCodeValid(code)) {
            SubscriptionPlan subscriptionPlan = this.subscriptionPlanRepository.findById(promoCode.getSubscriptionId()).orElseThrow(()-> new ResourceNotFoundException("subscription","subscriptionId ", promoCode.getSubscriptionId()));;
            if(promoCode.getFlatDiscount() == 0.00) {
                subscriptionPlan.setSubscriptionPrice(subscriptionPlan.getSubscriptionPrice() - (promoCode.getDiscountPercentage()/100)*subscriptionPlan.getSubscriptionPrice());
            }
            subscriptionPlan.setSubscriptionPrice(subscriptionPlan.getSubscriptionPrice() - promoCode.getFlatDiscount());
            return subscriptionPlan;
        }
        else {
            return new ApiResponse("promoCode is inValid",false);
        }
    }

}
