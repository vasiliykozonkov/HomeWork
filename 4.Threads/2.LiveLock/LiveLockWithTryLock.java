import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class LiveLockWithTryLock {
    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🟡 LIVELOCK через ReentrantLock.tryLock() с откатом");
        System.out.println("Потоки пытаются захватить ресурсы, но при неудаче откатываются и пробуют снова.\n");

        Thread thread1 = new Thread(() -> {
            int attempts = 0;
            while (attempts < 10) {
                attempts++;
                System.out.println("Поток 1: Попытка №" + attempts + ". Пробую захватить Lock A...");
                
                if (lockA.tryLock()) {
                    try {
                        System.out.println("Поток 1: Lock A захвачен. Пробую захватить Lock B...");
                        
                        try { Thread.sleep(50); } catch (InterruptedException e) { return; }

                        if (lockB.tryLock()) {
                            try {
                                System.out.println("Поток 1: Lock B захвачен! Работа выполнена!");
                                return;
                            } finally {
                                lockB.unlock();
                            }
                        } else {
                            System.out.println("Поток 1: Не смог захватить Lock B. Откатываюсь...");
                        }
                    } finally {
                        lockA.unlock();
                    }
                }
                
                try { Thread.sleep(random.nextInt(50)); } catch (InterruptedException e) { return; }
            }
            System.out.println("Поток 1: Лимит попыток исчерпан.");
        });

        Thread thread2 = new Thread(() -> {
            int attempts = 0;
            while (attempts < 10) {
                attempts++;
                System.out.println("Поток 2: Попытка №" + attempts + ". Пробую захватить Lock B...");
                
                if (lockB.tryLock()) {
                    try {
                        System.out.println("Поток 2: Lock B захвачен. Пробую захватить Lock A...");
                        
                        try { Thread.sleep(50); } catch (InterruptedException e) { return; }

                        if (lockA.tryLock()) {
                            try {
                                System.out.println("Поток 2: Lock A захвачен! Работа выполнена!");
                                return;
                            } finally {
                                lockA.unlock();
                            }
                        } else {
                            System.out.println("Поток 2: Не смог захватить Lock A. Откатываюсь...");
                        }
                    } finally {
                        lockB.unlock();
                    }
                }
                
                try { Thread.sleep(random.nextInt(50)); } catch (InterruptedException e) { return; }
            }
            System.out.println("Поток 2: Лимит попыток исчерпан.");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        
        System.out.println("\n✅ Программа завершена. Полезная работа не выполнена из-за постоянных откатов (LiveLock)!");
    }
}