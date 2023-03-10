package exercise.controller;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN

    @GetMapping("")
    public Iterable<User> getUsersByName(@RequestParam Map<String, String> user) {

        String firstName = user.get("firstName");
        String lastName = user.get("lastName");

        if (firstName == null && lastName == null) {
            return userRepository.findAll();
        }
//        Iterable<User> users = new ArrayList<>();
        if (firstName != null && lastName == null) {
            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        } else if (firstName == null) {
            return userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        } else if (lastName != null) {
            return userRepository.findAll(
                    QUser.user.firstName.containsIgnoreCase(firstName).and(
                            QUser.user.lastName.containsIgnoreCase(lastName)
                    )
            );
        }
        return null;













//    @GetMapping(path = "")
//    Iterable<User> getUsersByName(@RequestParam Map<String, String> user) {
//        return userRepository.findAll(QUser.user.firstName.toLowerCase().contains(user.get("firstName").toLowerCase())
//                .or(QUser.user.lastName.toLowerCase().contains(user.get("lastName").toLowerCase())));


//        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
//        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

//    public User getPerson(@PathVariable long id) {
//        return this.userRepository.findById(id);
//    }







    // Ищем всех пользователей с определенным именем
//    public Iterable<User> getUsersByName(String name) {
//        return userRepository.findAll(QUser.user.name.eq(name));
        // eq() – равно
//    }

//    // Ищем всех пользователей в возрастом в заданном диапазоне
//    // включая границы диапазона
//    public Iterable<User> getUsersByAge(Integer minAge, Integer maxAge) {
//        return userRepository.findAll(QUser.user.age.goe(minAge).and(QUser.user.age.loe(maxAge)));
//        // goe() – больше либо равно
//        // loe() – меньше либо равно
//    }
//
//    // Ищем всех пользователей, чьё имя не совпадает с переданным
//    public Iterable<User> getUsersExcludingName(String name) {
//        return repository.findAll(QUser.user.name.ne(name));
//        // ne() – не равно
//    }
    // END
}

