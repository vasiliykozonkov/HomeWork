public class AlternatingWaitNotify {
    // Общий объект для синхронизации (монитор)
    private static final Object lock = new Object();
    
    // Флаг: чья сейчас очередь? true = Поток 1, false = Поток 2
    private static boolean isThreadOneTurn = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🔄 Поочерёдный вывод через wait/notify (ручной способ)");
        System.out.println("Работает бесконечно. Нажми Ctrl+C для остановки.\n");

        // Поток 1: выводит "1"
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    // Ждём, пока не наша очередь (while защищает от ложных пробуждений!)
                    while (!isThreadOneTurn) {
                        try {
                            lock.wait(); // Освобождаем монитор и засыпаем
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    
                    // Наша очередь!
                    System.out.print("1 ");
                    isThreadOneTurn = false; // Передаём ход Потoku 2
                    lock.notify(); // Будим Поток 2
                }
            }
        });

        // Поток 2: выводит "2"
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    // Ждём, пока не наша очередь
                    while (isThreadOneTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    
                    // Наша очередь!
                    System.out.print("2 ");
                    isThreadOneTurn = true; // Передаём ход Потoku 1
                    lock.notify(); // Будим Поток 1
                }
            }
        });

        thread1.start();
        thread2.start();

        // Ждём бесконечно (потоки работают в фоне)
        thread1.join();
        thread2.join();
    }
}