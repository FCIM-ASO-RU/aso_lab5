package aso_lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.Random;

public class Philosopher extends Thread {

    boolean isConsuming;
    boolean forkFirst;
    private int count;
    private static final Logger logger = LogManager.getLogger(Philosopher.class);
    private final Random random;

    final  Fork forkLeft;
    final  Fork forkRight;

    Philosopher( String name, Fork _left, Fork _right ) {
        Configurator.initialize(null, "src/resource/log4j2.xml");
        isConsuming = false;
        forkFirst = false;
        count = 0;
        random = new Random();

        setName(name);
        forkLeft = _left;
        forkRight = _right;
    }
    
    @Override
    public void run() {
        logger.info("start");
        //System.out.println(getName()+ " : " + forkLeft.name + " - " + forkRight.name);
        try {
            while (true) {
                synchronized (this) {
                    int randomNumber = random.nextInt(5001);
                    forkFirst = randomNumber > 2500;
                    //if(forkFirst) logger.info("first fork is right"); else logger.info("first fork is left");
                    if (!forkRight.rt.isLocked() && !forkLeft.rt.isLocked() ) {
                        if (forkFirst) {
                            forkRight.rt.lock();
                            logger.info("{} (forkRight) is lock", forkRight.name);
                            sleep(random.nextInt(100));
                            if ( forkLeft.rt.tryLock()) {
                                logger.info("{} (forkLeft) is lock", forkLeft.name);
                            } else {
                                forkRight.rt.unlock();
                                logger.info("{} (forkRight) unlock", forkRight.name);
                            }
                        } else {
                            forkLeft.rt.lock();
                            logger.info("{} (forkLeft) is lock", forkLeft.name);
                            sleep(random.nextInt(100) + 300);
                            if (forkRight.rt.tryLock()) {
                                logger.info("{} (forkRight) is lock", forkRight.name);
                            } else {
                                forkLeft.rt.unlock();
                                logger.info("{} (forkLeft) unlock", forkLeft.name);
                            }
                        }
                    }
                    if ( forkLeft.rt.isHeldByCurrentThread() && forkRight.rt.isHeldByCurrentThread()) {
                        logger.info("consuming start");
                        count++;
                        sleep(random.nextInt(5000) + 500);
                        logger.info("consuming finish");
                        forkLeft.rt.unlock();
                        logger.info("{} (forkLeft) unlock", forkLeft.name);
                        forkRight.rt.unlock();
                        logger.info("{} (forkRight) unlock", forkRight.name);
                    } else {
                        if (forkLeft.rt.isHeldByCurrentThread()) forkLeft.rt.unlock();
                        if (forkRight.rt.isHeldByCurrentThread()) forkRight.rt.unlock();
                        sleep(random.nextInt(2000) + 300);
                    }
                    if ( count > 3) {
                        logger.info("break");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("catch exception.");
        }
    }
}