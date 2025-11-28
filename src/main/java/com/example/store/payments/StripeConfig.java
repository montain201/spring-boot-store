package com.example.store.payments;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {
    @Value("${stripe.secretkey}")
    private String secretkey;

    @PostConstruct
    public void init() {
        Stripe.apiKey= secretkey;
    }
}
