package aso_lab5;

public class Philosopher extends Thread {
    Chopstick leftChopstick;
    Chopstick rightChopstick;

    Philosopher(String name, Chopstick leftChopstick, Chopstick rightChopstick) {
        setName(name);
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    private void DoAction(String action) throws InterruptedException {
        System.out.println(getName() + ". Action: " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 10) {
                DoAction("Thinking");

                synchronized (leftChopstick) {
                    DoAction("Picked up left chopstick");

                    synchronized (rightChopstick) {
                        DoAction("Picked up right chopstick and started eating");

                        DoAction("Put down right chopstick");
                    }

                    DoAction("Put down left chopstick and started thinking");
                }

                ++i;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}