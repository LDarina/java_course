package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
  System.out.println("Hello, world!");

  Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

  Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


		Point p1 = new Point(1, 3);
    Point p2 = new Point(4,6);
    System.out.println("Расстояние между точками  = " + p1.distance(p2) );
  }

}

