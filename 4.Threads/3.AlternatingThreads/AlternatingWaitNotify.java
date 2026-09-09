public class AlternatingWaitNotify {
	private static final Object LOCK = new Object();
	private static volatile boolean isThreadOneTurn = true;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] Поочерёдный вывод через wait/notify");
		System.out.println("Работает бесконечно. Нажми Ctrl+C для остановки.\n");

		Thread thread1 = createThread("Поток 1", "1 ", true);
		Thread thread2 = createThread("Поток 2", "2 ", false);

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
	}

	private static Thread createThread(String name, String output, boolean isMyTurn) {
		return new Thread(() -> {
			while (true) {
				synchronized (LOCK) {
					while (isThreadOneTurn != isMyTurn) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
					System.out.print(output);
					isThreadOneTurn = !isMyTurn;
					LOCK.notifyAll();
				}
			}
		}, name);
	}
}