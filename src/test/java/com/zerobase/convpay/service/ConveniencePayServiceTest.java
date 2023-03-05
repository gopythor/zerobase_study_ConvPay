package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancleResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayCancelResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PayResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();

    @Test
        void pay_success () {
            //given
            PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 50);

            //when
            PayResponse payResponse = conveniencePayService.pay(payRequest);

            //then
            assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
            Assertions.assertEquals(35, payResponse.getPaidAmount());
        }

    @Test
    void pay_fail () {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1_500_001);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        Assertions.assertEquals(0, payResponse.getPaidAmount());
    }


    @Test
    void pay_cancel_success () {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 101);

        //when
        PayCancleResponse payCancleResponse = conveniencePayService.payCancel(payCancelRequest);

        //then
        assertEquals(PayCancelResult.PAY_CANCEL_SUCCESS, payCancleResponse.getPayCancelResult());
        Assertions.assertEquals(101, payCancleResponse.getPayCanceledAmount());
    }

    @Test
    void pay_cancel_fail () {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 99);

        //when
        PayCancleResponse payCancleResponse = conveniencePayService.payCancel(payCancelRequest);

        //then
        assertEquals(PayCancelResult.PAY_CANCEL_FAIL, payCancleResponse.getPayCancelResult());
        Assertions.assertEquals(0, payCancleResponse.getPayCanceledAmount());
    }
}