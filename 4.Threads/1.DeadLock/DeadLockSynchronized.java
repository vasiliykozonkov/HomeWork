public class DeadLockSynchronized {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🔴 DEADLOCK через synchronized (ручной способ)");
        System.out.println("Потоки заблокируются. Программа сама завершится через 5 секунд.\n");

        // 🐕 WATCHDOG-ПОТОК: через 5 секунд завершит программу
        Thread watchdog = new Thread(() -> {
            try {
                Thread.sleep(5000); // спим 5 секунд
                System.out.println("\n⏰ Watchdog: 5 секунд прошло, принудительное завершение!");
                System.exit(0); // принудительно завершаем JVM
            } catch (InterruptedException e) {
                // ничего не делаем
            }
        });
        watchdog.setDaemon(true); // демон-поток (не мешает завершению)
        watchdog.start();

        // Поток 1: захватывает A, потом пытается захватить B
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Поток 1: захватил ресурс A");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                System.out.println("Поток 1: ждёт ресурс B... (DEADLOCK)");
                synchronized (resourceB) {
                    System.out.println("Поток 1: захватил ресурс B");
                }
            }
        });

        // Поток 2: захватывает B, потом пытается захватить A
        Thread thread2 = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println("Поток 2: захватил ресурс B");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                System.out.println("Поток 2: ждёт ресурс A... (DEADLOCK)");
                synchronized (resourceA) {
                    System.out.println("Поток 2: захватил ресурс A");
                }
            }
        });

        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
    }
}