package lesson5.Updated;

import java.util.concurrent.*;

public class MainApp5Updated {
    public static final int CARS_COUNT = 10   ;
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        // Синхронизация старта
        CyclicBarrier startCyclicBarrier = new CyclicBarrier(CARS_COUNT + 1);

        // Ограничение въезда в тоннель
        Semaphore tunnelSemaphore = new Semaphore(CARS_COUNT / 2);
        // Оповещение о победителе
        Semaphore resultSemaphore = new Semaphore(1);

        // Синхронизация оповещения старта
        CountDownLatch startCountDownLatch = new CountDownLatch(CARS_COUNT);
        // Синхронизация оповещения завершения гонки
        CountDownLatch endCountDownLatch = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(80, tunnelSemaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), startCyclicBarrier, startCountDownLatch, endCountDownLatch, resultSemaphore);
        }
        try {
            for (int i = 0; i < cars.length; i++) {
                int forI = i;
                new Thread(cars[forI]).start();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            // Используем startCountDownLatch, чтобы сообщение о начале гонки было выведено
            // после подготовки всех участников, но до старта их действий (старт действий
            // ограничивается через startCyclicBarrier).
            startCountDownLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            startCyclicBarrier.await();
            // При появлении победителя происходит оповещение,
            // но участники могут доезжать и после сообщения c указанием их позиции.
            endCountDownLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }
}

