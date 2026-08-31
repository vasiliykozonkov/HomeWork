import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class DeadLockWithTimeout {
    // Создаем явные замки вместо обычных объектов
    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("⏳ DEADLOCK с таймаутом (ReentrantLock)");
        System.out.println("Потоки будут пытаться захватить ресурсы, но сдадутся через 2 секунды.\n");

        // Поток 1
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Поток 1: захватываю Lock A...");
                lockA.lock(); // Захватываем первый замок
                System.out.println("Поток 1: Lock A захвачен!");
                Thread.sleep(100); // Имитация работы

                System.out.println("Поток 1: пытаюсь захватить Lock B (жду 2 сек)...");
                // Пытаемся захватить второй замок с таймаутом!
                boolean gotLockB = lockB.tryLock(2, TimeUnit.SECONDS);

                if (gotLockB) {
                    System.out.println("Поток 1: Lock B захвачен! Работа выполнена.");
                    lockB.unlock();
                } else {
                    System.out.println("Поток 1: не смог захватить Lock B за 2 сек. Отменяю операцию!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Обязательно освобождаем замок, если мы его держим
                if (lockA.isHeldByCurrentThread()) {
                    lockA.unlock();
                    System.out.println("Поток 1: освободил Lock A");
                }
            }
        });

        // Поток 2
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Поток 2: захватываю Lock B...");
                lockB.lock(); // Захватываем первый замок
                System.out.println("Поток 2: Lock B захвачен!");
                Thread.sleep(100); // Имитация работы

                System.out.println("Поток 2: пытаюсь захватить Lock A (жду 2 сек)...");
                // Пытаемся захватить второй замок с таймаутом!
                boolean gotLockA = lockA.tryLock(2, TimeUnit.SECONDS);

                if (gotLockA) {
                    System.out.println("Поток 2: Lock A захвачен! Работа выполнена.");
                    lockA.unlock();
                } else {
                    System.out.println("Поток 2: не смог захватить Lock A за 2 сек. Отменяю операцию!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Обязательно освобождаем замок, если мы его держим
                if (lockB.isHeldByCurrentThread()) {
                    lockB.unlock();
                    System.out.println("Поток 2: освободил Lock B");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        
        System.out.println("\n✅ Программа успешно завершена без вечного зависания!");
    }
}