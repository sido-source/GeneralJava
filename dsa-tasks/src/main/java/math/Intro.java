package math;

import java.util.Random;

public class Intro {

    public static void main(String[] args) {
        // Math.pow()
        // Math.sqrt()
        // Math.round()
        // Math.abs()
        // Math.floor()
        // Math.ceil()
        // Math.max()
        // Math.min()
        Random rand = new Random();
        int i = rand.nextInt(100) - 50;

        double pow = Math.pow(2, 3);
        Integer integer = (int) pow;
        System.out.println(integer);


        double sqrt = Math.sqrt(25);
        System.out.println(sqrt);

        long round = Math.round(2.5);
        System.out.println(round);

        long round1 = Math.round(2.4);
        System.out.println(round1);

        int floor = (int) Math.floor(2.9);
        int ceil = (int) Math.ceil(2.1);
        System.out.println("floor: " + floor + " ceil: " + ceil);

        int r = (int) round;
        System.out.println(r);

        //int
        int abs = Math.abs(-5);
        int bigger = Math.max(-2, 10);
        System.out.println(bigger);

        //double
        double one = 12.3, two=1.4;
        double max = Math.max(one, two);

    }
}
