package aso_lab5;

public class Main {
    public static void main(String[] args) {
        int number = 8;

        Philosopher[] philosophers = new Philosopher[number];
        Fork[] fork = new Fork[number];

        for (int i=0; i< number; i++)
            fork[i] = new Fork("fork "+(i+1));

        philosophers[0] = new Philosopher("Philosopher 1", fork[number-1], fork[0] );
        for (int i=1; i < number; i++)
            philosophers[i] = new Philosopher("Philosopher "+(i+1), fork[i-1], fork[i] );

        for (int i=0; i < number; i++)
            philosophers[i].start();
    }
}