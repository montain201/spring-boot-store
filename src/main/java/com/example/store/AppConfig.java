package com.example.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${payment-gateway:stripe}")
    private String paymentGateway;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PayPalPaymentService();
    }
    @Bean
    public OrderServicePayment orderServicePayment() {
        if(paymentGateway.equals("stripe")) {

            return new OrderServicePayment(stripe());
        }
        return new OrderServicePayment(paypal());
    }


}
