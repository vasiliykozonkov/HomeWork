import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockSolution {
	private static final ReentrantLock LOCK_A = new ReentrantLock();
	private static final ReentrantLock LOCK_B = new ReentrantLock();
	private static final int MAX_ATTEMPTS = 10;
	private static final int MIN_SLEEP_MS = 50;
	private static final int MAX_SLEEP_MS = 200;
	private static final Random RANDOM = new Random();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] РЕШЕНИЕ LIVELOCK: Случайные задержки (Random Backoff)");
		System.out.println("Добавляем случайную паузу, чтобы рассинхронизировать потоки.\n");

		Thread thread1 = createThread("Поток 1", LOCK_A, LOCK_B);
		Thread thread2 = createThread("Поток 2", LOCK_B, LOCK_A);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		System.out.println("\n[INFO] Программа завершена. Благодаря случайности один из потоков выполнил работу!");
	}

	private static Thread createThread(String name, ReentrantLock firstLock, ReentrantLock secondLock) {
		return new Thread(() -> {
			int attempts = 0;
			while (attempts < MAX_ATTEMPTS) {
				attempts++;
				System.out.println(name + ": Попытка №" + attempts);

				if (firstLock.tryLock()) {
					try {
						try {
							Thread.sleep(RANDOM.nextInt(MAX_SLEEP_MS));
						} catch (InterruptedException e) {
							return;
						}

						if (secondLock.tryLock()) {
							try {
								System.out.println("[SUCCESS] " + name + ": Захватил оба замка! Работа выполнена.");
								return;
							} finally {
								secondLock.unlock();
							}
						}
					} finally {
						firstLock.unlock();
					}
				}
				try {
					Thread.sleep(RANDOM.nextInt(MAX_SLEEP_MS));
				} catch (InterruptedException e) {
					return;
				}
			}
			System.out.println(name + ": Не успел за " + MAX_ATTEMPTS + " попыток.");
		}, name);
	}
}