public class DeadLockSolution {
	
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("✅ РЕШЕНИЕ DEADLOCK: Упорядочивание блокировок");
        System.out.println("Оба потока захватывают ресурсы в ОДИНАКОВОМ порядке: сначала A, потом B.\n");

        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Поток 1: захватил ресурс A");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (resourceB) {
                    System.out.println("Поток 1: захватил ресурс B. Работа выполнена!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Поток 2: захватил ресурс A");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (resourceB) {
                    System.out.println("Поток 2: захватил ресурс B. Работа выполнена!");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        
        System.out.println("\n✅ Программа успешно завершена! Deadlock не возник.");
    }
}