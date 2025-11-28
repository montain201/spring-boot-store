package com.example.store;

//@Service
public class OrderServicePayment {

    private  PaymentService paymentService;

    public OrderServicePayment(PaymentService  paymentService) {
        this.paymentService = paymentService;
    }
    public void placeOrder() {
        paymentService.processPayment(10);
    }
//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
}
