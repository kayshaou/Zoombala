package com.jakesmommy.discountmanagement.util;

import com.jakesmommy.discountmanagement.domain.PromoCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.jakesmommy.discountmanagement.constants.DiscountCodeConstants.DEFAULT_DISCOUNT;

@Slf4j
public class CodeUtils {

    public final static Supplier<String> generateRandomCode = () -> RandomStringUtils.randomAlphabetic(7);


    public final static BiFunction<Double, Double, Double> calculate = (beforeDiscAmt, discountPerc) -> beforeDiscAmt - (beforeDiscAmt * (discountPerc / 100));

    public final static Supplier<PromoCode> getDefaultPromoCode = () ->
            PromoCode
                    .builder()
                    .uuid(UUID.randomUUID())
                    .discountPercentage(DEFAULT_DISCOUNT)
                    .code(generateRandomCode.get())
                    .build();

}
