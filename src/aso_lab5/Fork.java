package aso_lab5;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    public String name;
    public final ReentrantLock rt;

    public Fork(String _name) {
        name = _name;
        rt = new ReentrantLock();
    }
}
