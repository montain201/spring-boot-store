package com.example.store.users;

import com.example.store.products.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;



    public Iterable<UserDto> getAllUsers(String sortBy) {
        if(!Set.of("name","email").contains(sortBy))
            sortBy = "name";
        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    public UserDto getUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return userMapper.toDto(user);
    }
    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException();
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

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

    public UserDto updateUser(Long id, UpdateUserRequest request) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.update(request,user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
    public void deleteUser(Long userId){
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword()))
            throw new AccessDeniedException("Password does not match");
        user.setPassword(request.getNewPassword());
        userRepository.save(user);

    }


}
