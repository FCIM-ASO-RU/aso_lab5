package aso_lab5;

public class Main {
    public static void main(String[] args) {
        int number = 0;

        Philosopher[] philosophers = new Philosopher[number];

        for (int i=0; i<number; i++)
            philosophers[i] = new Philosopher();
        for (int i=0; i<number; i++)
            philosophers[i].start();
    }
}