package task1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation {

    private final Semaphore semaphore = new Semaphore(3);

    private final Fuel fuel = new Fuel(1000);

    void doRefuel() {
        try {
            semaphore.acquire();
            try {
                Thread.sleep((long) ((Math.random() * 8 + 3) * 1000));
                fuel.setAmount(fuel.getAmount() - 10);
                System.out.println(fuel.getAmount());
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
