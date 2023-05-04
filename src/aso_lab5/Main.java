package aso_lab5;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int number = 12;
        //Счетчик, который считает количество раз сколько был вызван метод countDown, и когда
        // достигается определенное число(12) то await() позволяет потоку дальше работать
        CountDownLatch doneSignal = new CountDownLatch(number);
        //Создаем объект обед где будут 12 философов обедать
        Dinner dinner = new Dinner(number);
        Philosopher[] philosophers = new Philosopher[number];
        //Создаем 12 философов и тем что они будут обедать одновременно
        for (int i = 0; i < number; i++) {
            philosophers[i] = new Philosopher(dinner, i, doneSignal);
        }
        //запускаем в потоке

        for (int i = 0; i < number; i++) {
            philosophers[i].start();
        }

        //Ждем пока все потоки отработают
        doneSignal.await();
        //Выводим информацию если все поели
        for (int i = 0; i < number; i++) {
            System.out.println("Philosopher #" + i +  " is full?  " +  Philosopher.State.Full.equals(philosophers[i].currentState));
        }
    }
}