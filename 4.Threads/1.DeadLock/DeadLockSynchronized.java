import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DeadLockSynchronized {
	private static final Object RESOURCE_A = new Object();
	private static final Object RESOURCE_B = new Object();
	private static final long SLEEP_MS = 100;
	private static final long WATCHDOG_DELAY_MS = 5000;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[ERROR] DEADLOCK через synchronized (ручной способ)");
		System.out.println("Потоки заблокируются. Диагностика через 5 секунд.\n");

		Thread watchdog = new Thread(() -> {
			try {
				Thread.sleep(WATCHDOG_DELAY_MS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
			ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
			long[] deadlockedThreads = threadBean.findDeadlockedThreads();
			if (deadlockedThreads != null) {
				System.out.println("\n[ERROR] Watchdog: Обнаружен DEADLOCK! Заблокированные потоки:");
				for (long threadId : deadlockedThreads) {
					System.out.println("  - " + threadBean.getThreadInfo(threadId).getThreadName());
				}
			} else {
				System.out.println("\n[INFO] Watchdog: Дедлок не обнаружен за 5 секунд.");
			}
		});
		watchdog.setDaemon(true);
		watchdog.start();

		Thread thread1 = createThread("Поток 1", RESOURCE_A, RESOURCE_B);
		Thread thread2 = createThread("Поток 2", RESOURCE_B, RESOURCE_A);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
	}

	private static Thread createThread(String name, Object firstLock, Object secondLock) {
		return new Thread(() -> {
			synchronized (firstLock) {
				System.out.println(name + ": захватил ресурс " + firstLock);
				try {
					Thread.sleep(SLEEP_MS);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
				System.out.println(name + ": ждёт ресурс " + secondLock + "... (DEADLOCK)");
				synchronized (secondLock) {
					System.out.println(name + ": захватил ресурс " + secondLock);
				}
			}
		}, name);
	}
}