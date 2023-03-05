package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class ApplicationConfig {

    @Bean
    public ConveniencePayService conveniencePayService(){
        return new ConveniencePayService(
                new HashSet<>(Arrays.asList(MoneyAdapter(), CardAdapter())
                ),
                getDiscountedByConvenience()
        );
    }

    @Bean
    public CardAdapter CardAdapter() {
        return new CardAdapter();
    }

    @Bean
    public MoneyAdapter MoneyAdapter() {
        return new MoneyAdapter();
    }

    @Bean
    public DiscountedByConvenience getDiscountedByConvenience() {
        return new DiscountedByConvenience();
    }


}
