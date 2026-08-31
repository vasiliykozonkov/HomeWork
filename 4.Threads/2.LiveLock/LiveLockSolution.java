import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class LiveLockSolution {
    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("✅ РЕШЕНИЕ LIVELOCK: Случайные задержки (Random Backoff)");
        System.out.println("Добавляем случайную паузу, чтобы рассинхронизировать потоки.\n");

        Thread thread1 = new Thread(() -> {
            int attempts = 0;
            while (attempts < 10) {
                attempts++;
                System.out.println("Поток 1: Попытка №" + attempts);

                if (lockA.tryLock()) {
                    try {
                        try { Thread.sleep(random.nextInt(100)); } catch (InterruptedException e) { return; }

                        if (lockB.tryLock()) {
                            try {
                                System.out.println("🎉 Поток 1: Захватил оба замка! Работа выполнена.");
                                return;
                            } finally {
                                lockB.unlock();
                            }
                        }
                    } finally {
                        lockA.unlock();
                    }
                }
                
                try { Thread.sleep(random.nextInt(200)); } catch (InterruptedException e) { return; }
            }
            System.out.println("Поток 1: Не успел за 10 попыток.");
        });

        Thread thread2 = new Thread(() -> {
            int attempts = 0;
            while (attempts < 10) {
                attempts++;
                System.out.println("Поток 2: Попытка №" + attempts);

                if (lockB.tryLock()) {
                    try {
                        try { Thread.sleep(random.nextInt(100)); } catch (InterruptedException e) { return; }

                        if (lockA.tryLock()) {
                            try {
                                System.out.println("🎉 Поток 2: Захватил оба замка! Работа выполнена.");
                                return;
                            } finally {
                                lockA.unlock();
                            }
                        }
                    } finally {
                        lockB.unlock();
                    }
                }
                
                try { Thread.sleep(random.nextInt(200)); } catch (InterruptedException e) { return; }
            }
            System.out.println("Поток 2: Не успел за 10 попыток.");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        
        System.out.println("\n✅ Программа завершена. Благодаря случайности один из потоков выполнил работу!");
    }
}