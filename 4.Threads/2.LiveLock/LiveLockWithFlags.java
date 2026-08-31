public class LiveLockWithFlags {
    static int attempts = 0;
    static final int MAX_ATTEMPTS = 10;
    
    static boolean isThread1Polite = true;
    static boolean isThread2Polite = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(" LIVELOCK через synchronized и флаги (ручной способ)");
        System.out.println("Потоки работают, но постоянно уступают друг другу.\n");

        Thread t1 = new Thread(() -> {
            while (attempts < MAX_ATTEMPTS) {
                synchronized (LiveLockWithFlags.class) {
                    attempts++;
                    System.out.println("Поток 1: Попытка №" + attempts);
                    
                    if (isThread2Polite) {
                        System.out.println("Поток 1: Вижу, Поток 2 готов. Уступаю ему...");
                        isThread1Polite = false;
                        try { Thread.sleep(50); } catch (InterruptedException e) {}
                        isThread1Polite = true;
                    } else {
                        System.out.println("Поток 1: О, второй уступил! Делаю полезную работу!");
                        isThread2Polite = false;
                    }
                }
            }
            System.out.println("Поток 1: Лимит попыток исчерпан, завершаюсь.");
        });

        Thread t2 = new Thread(() -> {
            while (attempts < MAX_ATTEMPTS) {
                synchronized (LiveLockWithFlags.class) {
                    if (attempts >= MAX_ATTEMPTS) break;
                    
                    System.out.println("Поток 2: Попытка №" + attempts);
                    
                    if (isThread1Polite) {
                        System.out.println("Поток 2: Вижу, Поток 1 готов. Уступаю ему...");
                        isThread2Polite = false;
                        try { Thread.sleep(50); } catch (InterruptedException e) {}
                        isThread2Polite = true;
                    } else {
                        System.out.println("Поток 2: О, первый уступил! Делаю полезную работу!");
                        isThread1Polite = false;
                    }
                }
            }
            System.out.println("Поток 2: Лимит попыток исчерпан, завершаюсь.");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
        System.out.println("\n✅ Программа завершена. (Полезная работа так и не была выполнена из-за LiveLock!)");
    }
}