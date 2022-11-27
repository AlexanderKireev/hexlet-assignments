package exercise;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class App {
    public static void main(String[] args) throws NegativeRadiusException {
        System.out.println("Проверка");///////////////////////////////////////

        Point point = new Point(3, 4);
        Circle circle = new Circle(point, 1);
        System.out.println(circle.getRadius()); // 1
        System.out.println(circle.getSquare()); // 3.1415...


        Point point2 = new Point(5, 7);
        Circle circle2 = new Circle(point2, 4);
        App.printSquare(circle2);


        Circle circle3 = new Circle(point, -2);
        App.printSquare(circle3);







//        System.out.println(App.printSquare(circle3));


    }

// BEGIN
    public static void printSquare(Circle circle) {
        try {
            System.out.println(Math.round(circle.getSquare()) + "\nВычисление окончено");
        } catch (NegativeRadiusException e) {
            System.out.println(e.getErrorDescription());
//            System.out.println("Не удалось посчитать площадь\nВычисление окончено");
        }
    }
// END

}





















