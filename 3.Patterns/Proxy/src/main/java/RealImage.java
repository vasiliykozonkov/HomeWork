public class RealImage implements Image {
	private String fileName;
	private String data;

	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromNetwork();
	}

	private void loadFromNetwork() {
		System.out.println("    Загрузка изображения: " + fileName + "...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = "Данные изображения " + fileName;
		System.out.println("   ✅ Изображение " + fileName + " загружено!");
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