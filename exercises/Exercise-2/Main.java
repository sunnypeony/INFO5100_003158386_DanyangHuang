import java.lang.Math;

enum Color {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    CYANOGEN,
    BLUE,
    PURPLE
}

abstract class Shape {
    protected double area;
    protected double perimeter;
    protected static Color color;

    public Shape() {
        color = Color.GREEN;
    }
    
    @SuppressWarnings("static-access")
    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @SuppressWarnings("static-access")
    public void setColor(Color color) {
        this.color = color;
    }

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

}

class Triangle extends Shape implements java.io.Serializable{
    public float edgeA;
    public float edgeB;
    public float angleAB;// in degree

    public Triangle(float edgeA, float edgeB, float angleAB) {
        this.edgeA = edgeA;
        this.edgeB = edgeB;
        this.angleAB = angleAB;
    }

    @Override
    public double calculateArea() {
        double radians = Math.toRadians(angleAB);
        return 0.5f* edgeA * Math.sin(radians);
    }

    @Override
    public double calculatePerimeter() {
        double radians = Math.toRadians(angleAB);
        double edgeC = Math.sqrt(edgeA*edgeA + edgeB*edgeB - 2*edgeA*edgeB*Math.cos(radians));
        return (edgeA + edgeB + edgeC);
    }

}

class Rectangle extends Shape {
    public float length;
    public float width;

    public Rectangle(float length, float width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

}

class Circle extends Shape {
    private double radius;

    public Circle(double radius, Color color) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * (radius * radius);
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

}

class Square extends Shape {
    public double side_length;

    public Square(float side_length) {
        this.side_length = side_length;
    }

    @Override
    public double calculateArea() {
        return side_length * side_length;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side_length;
    }
}


public class Main {
    public static void main(String[] args) {

        Triangle triangle = new Triangle(3, 4, 90);
        Rectangle rectangle = new Rectangle(3, 4);
        Circle circle = new Circle(3.0,Color.RED);
        Square square = new Square(2);

        System.out.println("======= Triangle =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n, ", triangle.calculateArea(), triangle.calculatePerimeter(), Shape.color.name());
        System.out.println("======= Rectangle =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n", rectangle.calculateArea(), rectangle.calculatePerimeter(),Shape.color);
        System.out.println("======= Circle =======");
        System.out.println("Color: " + circle.getColor());
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n", circle.calculateArea(), circle.calculatePerimeter(),Shape.color);
        System.out.println("======= Square =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n", square.calculateArea(), square.calculatePerimeter(),Shape.color);
    }

}
