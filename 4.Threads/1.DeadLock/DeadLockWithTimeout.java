import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockWithTimeout {
	private static final ReentrantLock LOCK_A = new ReentrantLock();
	private static final ReentrantLock LOCK_B = new ReentrantLock();
	private static final long SLEEP_MS = 100;
	private static final long TIMEOUT_SECONDS = 2;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] DEADLOCK с таймаутом (ReentrantLock)");
		System.out.println("Потоки будут пытаться захватить ресурсы, но сдадутся через " + TIMEOUT_SECONDS + " сек.\n");

		Thread thread1 = createThread("Поток 1", LOCK_A, LOCK_B);
		Thread thread2 = createThread("Поток 2", LOCK_B, LOCK_A);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		System.out.println("\n[INFO] Программа успешно завершена без вечного зависания!");
	}

	private static Thread createThread(String name, ReentrantLock firstLock, ReentrantLock secondLock) {
		return new Thread(() -> {
			try {
				System.out.println(name + ": захватываю " + firstLock + "...");
				firstLock.lock();
				System.out.println(name + ": " + firstLock + " захвачен!");
				Thread.sleep(SLEEP_MS);

				System.out.println(name + ": пытаюсь захватить " + secondLock + " (жду " + TIMEOUT_SECONDS + " сек)...");
				boolean gotLock = secondLock.tryLock(TIMEOUT_SECONDS, TimeUnit.SECONDS);

				if (gotLock) {
					System.out.println(name + ": " + secondLock + " захвачен! Работа выполнена.");
					secondLock.unlock();
				} else {
					System.out.println(name + ": не смог захватить " + secondLock + " за " + TIMEOUT_SECONDS + " сек. Отменяю операцию!");
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				if (firstLock.isHeldByCurrentThread()) {
					firstLock.unlock();
					System.out.println(name + ": освободил " + firstLock);
				}
			}
		}, name);
	}
}