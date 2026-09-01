public class RealImage implements Image {
    
    private final String fileName;
    private final String data; // Теперь final, так как инициализируется в конструкторе

    public RealImage(String fileName) {
        this.fileName = fileName;
        this.data = loadFromNetwork(); // Присваиваем результат сразу
    }

    private String loadFromNetwork() {
        System.out.println("    Загрузка изображения: " + fileName + "...");
        try {
            Thread.sleep(2000); // Имитация долгой загрузки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Хорошая практика при прерывании потока
        }
        System.out.println("   ✅ Изображение " + fileName + " загружено!");
        
        return "Данные изображения " + fileName;
    }

    @Override
    public void display() {
        System.out.println("   🖼️ Отображение: " + data);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}