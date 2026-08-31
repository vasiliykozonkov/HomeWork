public class AlternatingWaitNotify {
    private static final Object lock = new Object();
    
    private static boolean isThreadOneTurn = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🔄 Поочерёдный вывод через wait/notify (ручной способ)");
        System.out.println("Работает бесконечно. Нажми Ctrl+C для остановки.\n");

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isThreadOneTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    
                    System.out.print("1 ");
                    isThreadOneTurn = false;
                    lock.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isThreadOneTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    
                    System.out.print("2 ");
                    isThreadOneTurn = true;
                    lock.notify();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}