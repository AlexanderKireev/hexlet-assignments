package exercise;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

// BEGIN
@Value
@Getter
@Setter
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
