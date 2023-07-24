package com.example.crudapp.crudapp.Controller;

import com.example.crudapp.crudapp.Entity.SubscriptionPlan;
import com.example.crudapp.crudapp.Payloads.PromoCodeDto;
import com.example.crudapp.crudapp.Services.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promoCode")
public class PromoCodeController {

    @Autowired
    private PromoCodeService promoCodeService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PromoCodeDto> createPromoCode(@RequestBody PromoCodeDto promoCodeDto) {
        PromoCodeDto promoCodeDto1 = this.promoCodeService.createPromoCode(promoCodeDto);
        return new ResponseEntity<>(promoCodeDto1, HttpStatus.CREATED);
    }

    @GetMapping("/checkValid")
    public ResponseEntity<Boolean> checkPromoCode(@RequestParam String code) {
        Boolean isValid = this.promoCodeService.promoCodeValid(code);
        return new ResponseEntity<>(isValid,HttpStatus.OK);
    }

    @GetMapping("/applyPromo")
    public ResponseEntity<Object> applyPromo(@RequestParam String code) {
        Object obj = this.promoCodeService.applyPromoCode(code);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
