package exercise.controller;

import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Кодировщик BCrypt
    // Используйте для хеширования пароля
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = "")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void createUser(@RequestBody User user) {
        String password = user.getPassword();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
    }
    // END
}
