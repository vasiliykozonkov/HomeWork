public class EmailNotifier implements Notifier {
	private String email;

	public EmailNotifier(String email) {
		this.email = email;
	}

	@Override
	public void send(String message) {
		System.out.println(" Отправка Email на " + email + ": " + message);
	}
}