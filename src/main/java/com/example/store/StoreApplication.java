package com.example.store;

import com.example.store.entities.Address;
import com.example.store.entities.Profile;
import com.example.store.entities.Tag;
import com.example.store.entities.User;
import com.example.store.respositories.UserRepository;
import com.example.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
       /* var service = context.getBean(UserService.class);
        service.fetchProducts();*/
        //service.updateProductPrices();
        //service.persistRelated();
        //service.deleteRelated();
            //service.showEntityService();
        /*var user = User.builder()
                .name("Join")
                .email("john@gmail.com")
                .password("password")
                .build();
        repository.save(user);

        repository.findAll().forEach(u -> System.out.println(u.getEmail()));
        repository.deleteById(1L);*/
        //var orderService =  context.getBean(OrderService.class);
        //var orderService = new OrderService();
        //orderService.setPaymentService(new PayPalPaymentService());
        //orderService.placeOrder();
//
//        var user = new User();
//        user.setName("John");
//        user.setEmail("john.doe@gmail.com");
//        user.setPassword("1234");

        /*var user = User.builder()
                .name("John")
                .password("123")
                .email("john@gmail.com")
                .build();*/
        /*var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);*/
        //user.addTag("tag1");

       /* var profile =  Profile.builder().
                         bio("bio")
                        .build();
        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);*/
    }

}
