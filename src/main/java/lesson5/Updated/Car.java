package lesson5.Updated;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static int POSITION;
    private Race race;
    private int speed;
    private String name;

    CyclicBarrier startCyclicBarrier;

    CountDownLatch startCountDownLatch;
    CountDownLatch endCountDownLatch;

    Semaphore resultSemaphore;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier startCb, CountDownLatch startCdl, CountDownLatch endCdl, Semaphore resultS) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;

        startCyclicBarrier = startCb;
        startCountDownLatch = startCdl;
        endCountDownLatch = endCdl;
        resultSemaphore = resultS;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            startCountDownLatch.countDown();
            startCyclicBarrier.await();
            for (int i = 0; i < race.getStages().size(); i++) {
//                Stage stage = race.getStages().get(i);
//                if (stage instanceof Tunnel){
//
//                }
//                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);


                race.getStages().get(i).go(this);
            }
            resultSemaphore.acquire();
            POSITION++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (POSITION == 1) {
                System.out.println(this.name + " победил!");
            }
            else{
                System.out.println(this.name + " приехал " + POSITION);
            }
            resultSemaphore.release();
            endCountDownLatch.countDown();
        }
    }
}
