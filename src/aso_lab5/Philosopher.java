package aso_lab5;

// State : 2 = Eat, 1 = think
class Philosopher extends Thread
{
    private Chopstick _leftChopistick;
    private Chopstick _rightChopistick;

    private String _name;
    private int _state;
    private int _rounds;

    public Philosopher ( String name, Chopstick _left, Chopstick _right, int rounds){
        this._state = 1;
        this._name = name;
        _leftChopistick = _left;
        _rightChopistick = _right;
        _rounds = rounds;
    }

    public void eat() throws InterruptedException {
        if(! _leftChopistick.used){
            if(!_rightChopistick.used){
                _leftChopistick.take();
                _rightChopistick.take();

                System.out.println(_name + " : Eat");

                Thread.sleep(1000);

                _rightChopistick.release();
                _leftChopistick.release();
            }
        }
        think();
    }

    public void think() throws InterruptedException {
        this._state = 1;
        System.out.println(_name + " : Think");
        Thread.sleep(1000);

    }

    public void run(){
        for(int i=0; i<=_rounds; i++){
            try {
                eat();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
