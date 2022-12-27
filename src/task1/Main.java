package task1;

public class Main {

    static Thread[] threads = new Thread[100];

    public static void main(String[] args) {
        PetrolStation station = new PetrolStation();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(station::doRefuel);
        }
        for (Thread thread :
                threads) {
            thread.start();
        }
    }
}