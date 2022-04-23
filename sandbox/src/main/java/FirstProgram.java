public class FirstProgram {

    public static void main(String[] args) {
        System.out.println("Here we go again!");

        System.out.println();

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        System.out.println();

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        System.out.println();

        // Реализация номер один
        Point p1 = new Point("p1",7, 3);
        Point p2 = new Point("p2",2, 1);
        System.out.println("Точка p1 имеет координаты x = " + p1.x + ", y = " + p1.y + ".");
        System.out.println("Точка p2 имеет координаты x = " + p2.x + ", y = " + p2.y + ".");
        System.out.println("Расстояние между точками p1 и p2 составляет: " + distance(p1, p2));

        System.out.println();

        // Реализация номер два
        Point p3 = new Point("p3",7, 3);
        Point p4 = new Point("p4",2, 1);
        System.out.println("Точка " + p3.id + " имеет координаты x = " + p3.x + ", y = " + p3.y + ".");
        System.out.println("Точка " + p4.id + " имеет координаты x = " + p4.x + ", y = " + p4.y + ".");
        System.out.println("Расстояние между точками " + p3.id + " и " + p4.id + " составляет: " + p3.distanceToAnotherPoint(p4));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(square(p2.x - p1.x) + square(p2.y - p1.y));
    }

    static double square(double a){
        double result = a * a;
        return result;
    }
}