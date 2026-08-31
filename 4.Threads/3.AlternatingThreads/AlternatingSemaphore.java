import java.util.concurrent.Semaphore;

public class AlternatingSemaphore {
    
    private static final Semaphore sem1 = new Semaphore(1);
    
    private static final Semaphore sem2 = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🔄 Поочерёдный вывод через Semaphore (java.util.concurrent)");
        System.out.println("Работает бесконечно. Нажми Ctrl+C для остановки.\n");

        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    sem1.acquire();
                    System.out.print("1 ");
                    sem2.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    sem2.acquire();
                    System.out.print("2 ");
                    sem1.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}