package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// BEGIN

class App {

    public static void main(String[] args) {/////////////////////////////////
        System.out.println("Proverka");///////////////////////////////////////

        User owner = new User(1, "Ivanoff", "P", 25);
        // Вызываем автоматически сгенерированный геттер
        System.out.println(owner.getFirstName()); // "Ivan"

        Car car = new Car(1, "bmv", "x5", "black", owner);
        String jsonRepresentation1 = car.serialize();
        System.out.println(jsonRepresentation1);


        String jsonRepresentation = "{\"id\":5,\"brand\":\"audi\",\"model\":\"q7\",\"color\":\"white\",\"owner\":{\"id\":8,\"firstName\":\"Nikolay\",\"lastName\":\"Ivanov\",\"age\":50}}";
        Car instance = Car.unserialize(jsonRepresentation);
        System.out.println(instance.getBrand()); // "audi"
        System.out.println(instance.getModel()); // "q7"



        Path path1 = Paths.get("/tmp/file1.json");
        Car car1 = new Car(1, "audi", "q3", "black", owner);
        App.save(path1, car1); // Сохраняет представление объекта в файл

        Path path2 = Paths.get("/tmp/file1.json");
        Car car2 = App.extract(path2); // Возвращает инстанс класса Car
        System.out.println(car2.getModel()); // "passat"

    }

    public static void save(Path path, Car car) {
        Path fullPath = path.toAbsolutePath().normalize();
        try {
            Files.writeString(fullPath, car.serialize(), StandardOpenOption.WRITE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Car extract(Path path) {
        Path fullPath = path.toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Car.unserialize(content);
    }
}
// END
