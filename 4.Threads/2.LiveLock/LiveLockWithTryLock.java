import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockWithTryLock {
	private static final ReentrantLock LOCK_A = new ReentrantLock();
	private static final ReentrantLock LOCK_B = new ReentrantLock();
	private static final int MAX_ATTEMPTS = 10;
	private static final int SLEEP_MS = 50;
	private static final Random RANDOM = new Random();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] LIVELOCK через ReentrantLock.tryLock() с откатом");
		System.out.println("Потоки пытаются захватить ресурсы, но при неудаче откатываются.\n");

		Thread thread1 = createThread("Поток 1", LOCK_A, LOCK_B);
		Thread thread2 = createThread("Поток 2", LOCK_B, LOCK_A);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		System.out.println("\n[INFO] Программа завершена. Полезная работа не выполнена из-за постоянных откатов.");
	}

	private static Thread createThread(String name, ReentrantLock firstLock, ReentrantLock secondLock) {
		return new Thread(() -> {
			int attempts = 0;
			while (attempts < MAX_ATTEMPTS) {
				attempts++;
				System.out.println(name + ": Попытка №" + attempts + ". Пробую захватить " + firstLock + "...");

				if (firstLock.tryLock()) {
					try {
						System.out.println(name + ": " + firstLock + " захвачен. Пробую захватить " + secondLock + "...");
						try {
							Thread.sleep(SLEEP_MS);
						} catch (InterruptedException e) {
							return;
						}

						if (secondLock.tryLock()) {
							try {
								System.out.println("[SUCCESS] " + name + ": " + secondLock + " захвачен! Работа выполнена!");
								return;
							} finally {
								secondLock.unlock();
							}
						} else {
							System.out.println(name + ": Не смог захватить " + secondLock + ". Откатываюсь...");
						}
					} finally {
						firstLock.unlock();
					}
				}
				try {
					Thread.sleep(RANDOM.nextInt(SLEEP_MS));
				} catch (InterruptedException e) {
					return;
				}
			}
			System.out.println(name + ": Лимит попыток исчерпан.");
		}, name);
	}
}