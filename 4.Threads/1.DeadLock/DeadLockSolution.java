public class DeadLockSolution {
	private static final Object RESOURCE_A = new Object();
	private static final Object RESOURCE_B = new Object();
	private static final long SLEEP_MS = 100;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] РЕШЕНИЕ DEADLOCK: Упорядочивание блокировок");
		System.out.println("Оба потока захватывают ресурсы в ОДИНАКОВОМ порядке: сначала A, потом B.\n");

		Thread thread1 = createThread("Поток 1", RESOURCE_A, RESOURCE_B);
		Thread thread2 = createThread("Поток 2", RESOURCE_A, RESOURCE_B);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		System.out.println("\n[INFO] Программа успешно завершена! Deadlock не возник.");
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
				synchronized (secondLock) {
					System.out.println(name + ": захватил ресурс " + secondLock + ". Работа выполнена!");
				}
			}
		}, name);
	}
}