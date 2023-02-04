package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
// Отмечаем класс, как контроллер
// Аннотация @RestController включает в себя аннотацию @Controller and @ResponseBody
// Упрощает создание контроллера
// При использовании @RestController, использовать аннотацию @ResponseBody нет необходимости
@RestController
// Класс контроллера
public class WelcomeController {

    // Сопоставляем GET запрос на адрес /users с обработчиком
    @GetMapping("/")
    // Обработчик
    // Привязываем параметр запроса к параметру метода
    // и задаём значение по умолчанию
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String welcomeUser(@RequestParam(value = "name", defaultValue = "World!") String name) {
        return String.format("Hello, %s!", name);
    }

}
// END
