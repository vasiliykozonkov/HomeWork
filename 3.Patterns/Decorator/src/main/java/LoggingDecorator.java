public class LoggingDecorator extends NotifierDecorator {

    public LoggingDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        System.out.println("📝 [ЛОГ] Начало отправки: " + System.currentTimeMillis());
        
        super.send(message);
        
        System.out.println("✅ [ЛОГ] Отправка завершена: " + System.currentTimeMillis());
    }
}