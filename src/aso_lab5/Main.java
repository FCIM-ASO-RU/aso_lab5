package aso_lab5;

public class Main {
    public static void main(String[] args) {
        int n = 6;
        Philosopher[] philosophers = new Philosopher[n];
        Fork[] forks = new Fork[n];

        for (int i = 0; i < n; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < n; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % n]);
            philosophers[i].start();
        }
    }   
}