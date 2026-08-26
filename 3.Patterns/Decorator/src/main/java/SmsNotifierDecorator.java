public class SmsNotifierDecorator extends NotifierDecorator {
	private String phoneNumber;

	public SmsNotifierDecorator(Notifier notifier, String phoneNumber) {
		super(notifier);
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void send(String message) {

		super.send(message);

		System.out.println(" Отправка SMS на " + phoneNumber + ": " + message);
	}
}