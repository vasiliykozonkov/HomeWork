public class Main {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("   ПАТТЕРН PROXY: ЗАГРУЗКА ИЗОБРАЖЕНИЙ");
        System.out.println("═══════════════════════════════════════\n");

        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.jpg");
        Image image3 = new ImageProxy("photo1.jpg");

        System.out.println("🔹 Шаг 1: Создали прокси (загрузки ещё не было)\n");

        System.out.println("🔹 Шаг 2: Первый запрос к photo1.jpg");
        image1.display();
        System.out.println();

        System.out.println("🔹 Шаг 3: Повторный запрос к photo1.jpg");
        image1.display();
        System.out.println();

        System.out.println("🔹 Шаг 4: Запрос к photo2.jpg");
        image2.display();
        System.out.println();

        System.out.println("🔹 Шаг 5: Запрос к photo1.jpg через другой прокси");
        image3.display();
    }
}