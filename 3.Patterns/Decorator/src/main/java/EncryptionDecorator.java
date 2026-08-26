public class EncryptionDecorator extends NotifierDecorator {

	public EncryptionDecorator(Notifier notifier) {
		super(notifier);
	}

	@Override
	public void send(String message) {
		System.out.println("   🔐 Шифрование сообщения...");
		String encrypted = encrypt(message);
		System.out.println("   🔒 Зашифровано: " + encrypted);
		super.send(encrypted);
	}

	private String encrypt(String message) {

		return new StringBuilder(message).reverse().toString();
	}
}