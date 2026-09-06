public class LiveLockWithFlags {
	private static final Object MONITOR = new Object();
	private static volatile int attempts = 0;
	private static final int MAX_ATTEMPTS = 10;
	private static final long SLEEP_MS = 50;

	private static volatile boolean isThread1Polite = true;
	private static volatile boolean isThread2Polite = true;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("[INFO] LIVELOCK через synchronized и флаги");
		System.out.println("Потоки работают, но постоянно уступают друг другу.\n");

		Thread t1 = new Worker("Поток 1", true);
		Thread t2 = new Worker("Поток 2", false);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("\n[INFO] Программа завершена. Лимит попыток исчерпан из-за LiveLock.");
	}

	private static class Worker extends Thread {
		private final boolean isMyTurnFirst;

		Worker(String name, boolean isMyTurnFirst) {
			super(name);
			this.isMyTurnFirst = isMyTurnFirst;
		}

		@Override
		public void run() {
			while (true) {
				boolean shouldWork = false;

				synchronized (MONITOR) {
					if (attempts >= MAX_ATTEMPTS) {
						break;
					}
					attempts++;
					System.out.println(getName() + ": Попытка №" + attempts);

					boolean otherIsPolite = isMyTurnFirst ? isThread2Polite : isThread1Polite;

					if (otherIsPolite) {
						System.out.println(getName() + ": Вижу, другой поток готов. Уступаю ему...");
						if (isMyTurnFirst) {
							isThread1Polite = false;
						} else {
							isThread2Polite = false;
						}
					} else {
						System.out.println(getName() + ": О, другой уступил! Делаю полезную работу!");
						shouldWork = true;
						if (isMyTurnFirst) {
							isThread1Polite = true;
							isThread2Polite = true;
						} else {
							isThread2Polite = true;
							isThread1Polite = true;
						}
					}
				}

				try {
					Thread.sleep(SLEEP_MS);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}

				if (shouldWork) {
					System.out.println("[SUCCESS] " + getName() + ": Выполняю полезную работу!");
					break;
				}
			}
			System.out.println(getName() + ": Завершаюсь.");
		}
	}
}