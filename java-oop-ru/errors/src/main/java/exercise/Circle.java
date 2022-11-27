package exercise;

import exercise.exception.CustomException;

// BEGIN
public class Circle {
    private final int radius;
    public Circle(Point point, int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius >= 0) {
            return Math.PI * (radius * radius);
        }
        throw new NegativeRadiusException("Не удалось посчитать площадь\nВычисление окончено");
    }
}
// END
