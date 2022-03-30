package com.itechart.payment_service.util;

import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

@NoArgsConstructor
public class ElectronicPaymentSystem
{
    public boolean executePayment(String cardNumber, String validityPeriod, String cardCode, Double orderTotalPrice)
    {
        return new Random().nextDouble() < 0.9;
    }

    public String getTransactionNumber()
    {
        return RandomStringUtils.random(20, true, true);
    }
}
