package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start();
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }
    @AfterAll
    static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    public void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users")
                .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/new")
                .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateValidUser() {

        HttpResponse response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan666321")
                .field("lastName", "Ivanov")
                .field("email", "eee@mail.com")
                .field("password", "12345")
                .asEmpty();
        assertThat(response.getStatus()).isEqualTo(302);
        User DBUser = new QUser()
                .firstName.equalTo("Ivan666321")
                .findOne();
        assertThat(DBUser).isNotNull();
        assertThat(DBUser.getFirstName()).isEqualTo("Ivan666321");
        assertThat(DBUser.getLastName()).isEqualTo("Ivanov");
        assertThat(DBUser.getEmail()).isEqualTo("eee@mail.com");
        assertThat(DBUser.getPassword()).isEqualTo("12345");
    }
    @Test
    void testCreateInvalidUser() {
        HttpResponse response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan666321")
                .field("lastName", "Ivanov")
                .field("email", "eee")
                .field("password", "123456")
                .asEmpty();
        assertThat(response.getStatus()).isEqualTo(422);
        User DBUser = new QUser()
                .firstName.equalTo("Ivan666321")
                .findOne();
        assertThat(DBUser).isNull();
//        assertThat(response.getBody()).isEqualTo("Должно быть валидным email");
    }

    // END
}