package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN

//     people/hello?first_name=John;last_name=KKK
//    @GetMapping("/hello")
//    public void hello(@RequestParam(value = "first_name", defaultValue = "World!") String name) {
//        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
//        jdbc.update(query, name, name);
////        return String.format("Hello, %s!", name);
//    }


    @GetMapping(path = "")
    public List<Map<String, Object>> getPeople() {
        String query = "SELECT first_name, last_name FROM person";
        return jdbc.queryForList(query);
    }

    @GetMapping(path = "/{id}")
    public Map<String, Object> getPeopleById(@PathVariable String id) {
        String query = "SELECT first_name, last_name FROM person where id = ?";
        return jdbc.queryForMap(query, id);
    }


//    @GetMapping("")
//    public String hello() {
//        String json = DB.json().toJson(users);
//
//        return jdbc


//        List<User> users = new QUser()
//                .orderBy()
//                .id.asc()
//                .findList();
//        String json = DB.json().toJson(users);
//        ctx.json(json);
//
//        return "Hello, ";








    // END
}
