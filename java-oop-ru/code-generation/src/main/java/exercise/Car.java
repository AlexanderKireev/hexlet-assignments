package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
@AllArgsConstructor
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        Car c = new Car(getId(), this.brand, this.model, this.color, this.owner);
        try {
            json = mapper.writeValueAsString(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    public static Car unserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Car.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    // END
}
