package lesson5.Updated;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore tunnelSemaphore;

    public Tunnel(int l) {
        this.length = l;
        this.description = "Тоннель " + length + " метров";
    }

    public Tunnel(int l, Semaphore s) {
        this.length = l;
        this.description = "Тоннель " + length + " метров";
        this.tunnelSemaphore = s;
    }

    @Override
    public void go(Car c) {
        try {
            try {
                // Из-за сообщения ниже понадобилось создать новый конструктор.
                // Альтернативно, если это сообщение вынести выше в Car, то можно обойтись базовым конструктором.
                System.out.println(c.getName() + " готовится к этапу (ждет): " + description);
                tunnelSemaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            tunnelSemaphore.release();
        }
    }
}
