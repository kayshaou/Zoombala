package com.jakesmommy.discountmanagement.controller;

import com.github.mustachejava.Code;
import com.jakesmommy.discountmanagement.domain.Order;
import com.jakesmommy.discountmanagement.domain.PromoCode;
import com.jakesmommy.discountmanagement.util.CodeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jakesmommy.discountmanagement.domain.PromoCode;

import java.util.Optional;
import java.util.UUID;

import static com.jakesmommy.discountmanagement.constants.DiscountCodeConstants.DEFAULT_DISCOUNT;

@RestController
@RequestMapping("/v1/code")
public class PromoCodeController {

    @GetMapping("/firstTimer")
    public ResponseEntity<PromoCode> getFirstTimerDiscountCode() {
        return new ResponseEntity(CodeUtils.getDefaultPromoCode.get(), HttpStatus.OK);
    }

    @GetMapping("/subtotal")
    public ResponseEntity<Order> calculateSubTotal(@RequestBody Order order) {
        Double subTotal = CodeUtils.calculate.apply(order.getOrderTotal(),
                Optional.ofNullable(order.getPromoCode())
                .map(PromoCode::getDiscountPercentage).orElse(CodeUtils.getDefaultPromoCode.get().getDiscountPercentage()));
        order.setOrderSubTotal(subTotal);
        return new ResponseEntity(order, HttpStatus.OK);
    }

}
