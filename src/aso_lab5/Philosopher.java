package aso_lab5;

public class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        try {
            int i = 0;
            while (i<10) {
                if (leftFork.tryLock()) {
                    if (rightFork.tryLock()) {
                        eat();
                        rightFork.unlock();
                    }
                    leftFork.unlock();
                }
                think();
                i++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() throws InterruptedException {
        System.out.println("Филосов " + id + " начинает кушать");
        Thread.sleep(1000); // mâncare timp de 1 secundă
        System.out.println("Философ " + id + " заканчивает кушать");
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " думает");
        Thread.sleep(1000); // gândire timp de 1 secundă
    }
}