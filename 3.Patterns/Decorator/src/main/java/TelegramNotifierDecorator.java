public class TelegramNotifierDecorator extends NotifierDecorator {
	private String telegramId;

	public TelegramNotifierDecorator(Notifier notifier, String telegramId) {
		super(notifier);
		this.telegramId = telegramId;
	}

	@Override
	public void send(String message) {
		super.send(message);
		System.out.println("✈️ Отправка в Telegram @" + telegramId + ": " + message);
	}
}