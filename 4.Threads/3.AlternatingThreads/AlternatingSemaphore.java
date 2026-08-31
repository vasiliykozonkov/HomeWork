import java.util.concurrent.Semaphore;

public class AlternatingSemaphore {
    
    // Семафор для Потока 1: сразу даём 1 разрешение, чтобы он начал первым
    private static final Semaphore sem1 = new Semaphore(1);
    
    // Семафор для Потока 2: 0 разрешений, он будет ждать, пока Поток 1 его не пустит
    private static final Semaphore sem2 = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🔄 Поочерёдный вывод через Semaphore (java.util.concurrent)");
        System.out.println("Работает бесконечно. Нажми Ctrl+C для остановки.\n");

        // Поток 1: выводит "1"
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    sem1.acquire(); // Запрашиваем разрешение (если нет - ждём)
                    System.out.print("1 ");
                    sem2.release(); // Отдаём разрешение Потоку 2
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        // Поток 2: выводит "2"
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    sem2.acquire(); // Запрашиваем разрешение (ждём, пока Поток 1 не даст)
                    System.out.print("2 ");
                    sem1.release(); // Отдаём разрешение обратно Потоку 1
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}