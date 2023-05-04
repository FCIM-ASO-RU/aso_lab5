package aso_lab5;

public class Dinner {
    private final int number;
    private final State[] states;

    private enum State {
        Free,
        Busy
    }

    public Dinner(int number) {
        this.number = number;
        this.states = new State[number];
        for (int i = 0; i < number; i++) {
            states[i] = State.Free;
        }
        System.out.println("Dinner places are created!");
        printState();
    }

    public boolean takeDinner(int position) {
        synchronized (this){
            boolean success = false;
            //Синхронизируем данную часть, что бы один филлосов брал 1 место за раз и не было проблем с другими

            //% <- остаток, то есть все что меньше 12 будет возвращать себя же а если больше то остаток от деления
            int rightState = (position + 1) % number;
            if (states[position] == State.Free && states[rightState] == State.Free) {
                states[position] = State.Busy;
                states[rightState] = State.Busy;

                success = true;
            }
            printState();
            return success;
        }

    }

    public void leaveDinner(int position) {
        System.out.println("Philosopher #" + position + " has left the dinner.");
        printState();
        //освобождакем место
        synchronized (this){
            int rightState = (position + 1) % number;
            states[position] = State.Free;
            states[rightState] = State.Free;


        }

    }

    private synchronized void printState() {
        System.out.print("Current state: ");
        for (int i = 0; i < number; i++) {
            System.out.print(i + ":" + states[i] + "  ");
        }
        System.out.println();
    }

}

