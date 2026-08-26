public class ImageProxy implements Image {
	private String fileName;
	private RealImage realImage;

	public ImageProxy(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		System.out.println("📋 Запрос на отображение: " + fileName);

		if (realImage == null) {
			System.out.println("    Реальное изображение ещё не загружено, создаём...");
			realImage = new RealImage(fileName);
		} else {
			System.out.println("   🔹 Изображение уже в кэше, используем его!");
		}

		realImage.display();
	}

	@Override
	public String getFileName() {
		return fileName;
	}
}