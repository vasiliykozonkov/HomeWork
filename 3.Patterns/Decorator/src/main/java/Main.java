public class Main {
	public static void main(String[] args) {
		System.out.println("═══════════════════════════════════════");
		System.out.println("   ПАТТЕРН DECORATOR: СИСТЕМА УВЕДОМЛЕНИЙ");
		System.out.println("═══════════════════════════════════════\n");

		String email = "vasya@example.com";
		String phone = "+7 (999) 123-45-67";
		String telegram = "@vasya";
		String message = "Ваш заказ №1234 готов к выдаче!";

		System.out.println("📋 ПРИМЕР 1: Только Email");
		Notifier notifier1 = new EmailNotifier(email);
		notifier1.send(message);
		System.out.println();

		System.out.println("📋 ПРИМЕР 2: Email + SMS");
		Notifier notifier2 = new SmsNotifierDecorator(
			new EmailNotifier(email), phone
		);
		notifier2.send(message);
		System.out.println();

		System.out.println("📋 ПРИМЕР 3: Email + SMS + Telegram");
		Notifier notifier3 = new TelegramNotifierDecorator(
			new SmsNotifierDecorator(
				new EmailNotifier(email), phone
			), telegram
		);
		notifier3.send(message);
		System.out.println();

		System.out.println(" ПРИМЕР 4: Email + Шифрование + SMS");
		Notifier notifier4 = new SmsNotifierDecorator(
			new EncryptionDecorator(
				new EmailNotifier(email)
			), phone
		);
		notifier4.send(message);
		System.out.println();

		System.out.println("📋 ПРИМЕР 5: Email + Логирование + Шифрование + Telegram");
		Notifier notifier5 = new TelegramNotifierDecorator(
			new EncryptionDecorator(
				new LoggingDecorator(
					new EmailNotifier(email)
				)
			), telegram
		);
		notifier5.send(message);
	}
}