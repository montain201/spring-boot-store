package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.Category;
import com.example.store.entities.User;
import com.example.store.respositories.ProductRepository;
import com.example.store.respositories.ProfileRepository;
import com.example.store.respositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );

    }

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void showEntityService() {

        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("123")
                .build();

        if (entityManager.contains(user))
            System.out.println("Persistant");
        else
            System.out.println("Transient/Detached");

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistant");
        else
            System.out.println("Transient/Detached");
    }
    @Transactional
    public void showRelatedEntities(){
        var profile=profileRepository.findById(12L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
        //System.out.println(profile.getBio());;
        /*var user = userRepository.findById(12L).orElseThrow();
        System.out.println(user.getEmail());*/
    }
    public void persistRelated(){
        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("123")
                .build();
        var address = Address.builder()
                .street("street")
                .city("city")
                //.state("152L")
                .zip("zip")
                .build();

        user.addAddress(address);
        userRepository.save(user);

    }
    @Transactional
    public void deleteRelated(){
        //userRepository.deleteById(16L);
        var user = userRepository.findById(17L).orElseThrow();
        var address = user.getAddresses().get(0);
        //System.out.println(address);
        user.removeAddress(address);
        userRepository.save(user);
    }

    /*@Transactional
    public void manageProducts()
    {
        productRepository.deleteById(4L);
    }*/
    @Transactional
    public void updateProductPrices()
    {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(105),(byte)1);
    }

    /*public void fetchProducts(){
         var products = productRepository.findByCategory(new Category((byte)1));
         products.forEach(System.out::println);
    }*/
    @Transactional
    public void fetchProducts(){
        var products = productRepository.findProducts(BigDecimal.valueOf(5),BigDecimal.valueOf(15));
        products.forEach(System.out::println);
    }
}
