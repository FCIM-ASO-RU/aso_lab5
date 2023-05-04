package aso_lab5;

import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    private final int position;
    public State currentState;
    private final Dinner dinner;
    private final CountDownLatch countDownLatch;

    // приватный enum, внутренний так как он не разрывно связан с классом
    public enum State {
        Eating,
        Thinking,
        Full
    }


    public Philosopher(Dinner dinner, Integer position, CountDownLatch countDownLatch) {
        this.dinner = dinner;
        this.countDownLatch = countDownLatch;
        this.currentState = State.Thinking;
        this.position = position;
    }

    @Override
    public void run() {
        //Пока Филосов не сыт, он будет пытатся занять стол
        while (this.currentState != State.Full) {
            switch (this.currentState) {
                case Thinking: {
                    System.out.println("Philosopher #" + position + " is thinking to take a dinner");
                    //Если Филосов может занять два места (свое и следуещие) то он может поесть

                    try {
                        Thread.sleep( (int)(Math.random()*1000) );
                    } catch (Exception ignored){

                    }


                    if (dinner.takeDinner(position)) {
                        this.currentState = State.Eating;
                    }

                }
                case Eating: {
                    System.out.println("Philosopher #" + position + " ate");
                    //освобождаем место
                    dinner.leaveDinner(position);
                    this.currentState = State.Full;
                }
            }

        }

        //Так как цикл закончился, это значит что Филосов поел, так что +1 к счетчику
        countDownLatch.countDown();
    }
}
