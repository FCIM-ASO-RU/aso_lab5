package aso_lab5;

// Вариант = 15
// Кол-во философов = номер варианта + 6 = 15 + 6 = 21

public class Main {
    public static void main(String[] args) {
        System.out.println("Prutean & Neer:");

        int number = 21;
        
        Philosopher[] philosophers = new Philosopher[number];
        Chopstick[] chopsticks = new Chopstick[number];

        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Chopstick();
        }

        for (int i = 0; i < number; i++)
        {
            Chopstick leftChopstick = chopsticks[i];
            Chopstick rightChopstick = chopsticks[(i + 1) % chopsticks.length];

            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher("Philosopher " + (i + 1), rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher("Philosopher " + (i + 1), leftChopstick, rightChopstick);
            }
        }

        for (int i = 0; i < number; i++)
        {
            philosophers[i].start();
        }
    }   
}