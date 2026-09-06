import java.util.concurrent.Semaphore;

public class AlternatingSemaphore {
	private static final Semaphore SEM_1 = new Semaphore(1);
	private static final Semaphore SEM_2 = new Semaphore(0);

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] Поочерёдный вывод через Semaphore");
		System.out.println("Работает бесконечно. Нажми Ctrl+C для остановки.\n");

		Thread thread1 = createThread("Поток 1", "1 ", SEM_1, SEM_2);
		Thread thread2 = createThread("Поток 2", "2 ", SEM_2, SEM_1);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
	}

	private static Thread createThread(String name, String output, Semaphore mySem, Semaphore otherSem) {
		return new Thread(() -> {
			while (true) {
				try {
					mySem.acquire();
					System.out.print(output);
					otherSem.release();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		}, name);
	}
}