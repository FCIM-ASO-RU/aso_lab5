package aso_lab5;

public class Main {
    public static void main(String[] args){

        int rounds=10;

        System.out.println(rounds);

        Chopstick[] chopistics = new Chopstick[5];

        //initlize the chopistics
        for(int i=0; i< chopistics.length; i++){
            chopistics[i] = new Chopstick("C: "+i);
        }
        Philosopher[] philosophers = new Philosopher[5];
        //for(i=0; i<philosophers.length; i++){
        philosophers[0] = new Philosopher("P: 0 - ", chopistics[0], chopistics[1], rounds);
        philosophers[1] = new Philosopher("P: 1 - ", chopistics[1], chopistics[2], rounds);
        philosophers[2] = new Philosopher("P: 2 - ", chopistics[2], chopistics[3], rounds);
        philosophers[3] = new Philosopher("P: 3 - ", chopistics[3], chopistics[4], rounds);
        philosophers[4] = new Philosopher("P: 4 - ", chopistics[0], chopistics[4], rounds);

        for(int i=0;i<philosophers.length;i++){
            System.out.println("Thread "+ i + " has started");
            Thread t= new Thread( philosophers[i]);
            t.start();
        }
    }
}