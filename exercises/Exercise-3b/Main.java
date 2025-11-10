import java.lang.Math;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

enum Color {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    CYANOGEN,
    BLUE,
    PURPLE
}

abstract class Shape implements Serializable {
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

        public static void MySerialization(String name, Shape object) {
        try {
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/%s!\n", name);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Shape MyDeserialization(String name) {
        Shape object = null;
        try {
            FileInputStream fileIn = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = (Shape) in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found!");
            c.printStackTrace();
        }
        return object;
    }
    public static void main(String[] args) {

        Triangle triangle = new Triangle(3, 4, 90);
        Rectangle rectangle = new Rectangle(3, 4);
        Circle circle = new Circle(3.0,Color.RED);
        Square square = new Square(2);

               MySerialization("Triangle.ser", triangle);
        Triangle triangleDeser = (Triangle) MyDeserialization("Triangle.ser");

        MySerialization("Rectangle.ser", rectangle);
        Rectangle rectangleDeser = (Rectangle) MyDeserialization("Rectangle.ser");

        MySerialization("Circle.ser", circle);
        Circle circleDeser = (Circle) MyDeserialization("Circle.ser");

        MySerialization("Square.ser", square);
        Square squareDeser = (Square) MyDeserialization("Square.ser");


        System.out.println("Deserialized Classes are:");
        System.out.println("======= Deserialized object type: " + triangleDeser.getClass().getName() + " =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n, ", triangleDeser.calculateArea(),
                triangleDeser.calculatePerimeter(), Shape.color);
        System.out.println("======= Deserialized object type: " + rectangleDeser.getClass().getName() + " =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n", rectangleDeser.calculateArea(),
                rectangleDeser.calculatePerimeter(), Shape.color);
        System.out.println("======= Deserialized object type: " + circleDeser.getClass().getName() + " =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n", circleDeser.calculateArea(),
                circleDeser.calculatePerimeter(), Shape.color);
        System.out.println("======= Deserialized object type: " + squareDeser.getClass().getName() + " =======");
        System.out.printf("Area is: %f; Parimeter is: %f; Color is: %s\n", squareDeser.calculateArea(),
                squareDeser.calculatePerimeter(), Shape.color);
    }

}

