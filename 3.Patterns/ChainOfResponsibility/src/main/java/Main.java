public class Main {
	public static void main(String[] args) {

		ApprovalHandler teamLead = new TeamLeadHandler();
		ApprovalHandler manager = new ManagerHandler();
		ApprovalHandler director = new DirectorHandler();

		teamLead.setNext(manager).setNext(director);

		System.out.println("═══════════════════════════════════════");
		System.out.println("   СИСТЕМА ОДОБРЕНИЯ ЗАЯВОК");
		System.out.println("═══════════════════════════════════════\n");

		System.out.println("📋 ТЕСТ 1:");
		PurchaseRequest request1 = new PurchaseRequest("Вася", "Мышка", 3_500);
		System.out.println(request1);
		teamLead.handleRequest(request1);
		System.out.println();

		System.out.println("📋 ТЕСТ 2:");
		PurchaseRequest request2 = new PurchaseRequest("Петя", "Ноутбук", 35_000);
		System.out.println(request2);
		teamLead.handleRequest(request2);
		System.out.println();

		System.out.println("📋 ТЕСТ 3:");
		PurchaseRequest request3 = new PurchaseRequest("Маша", "Сервер", 150_000);
		System.out.println(request3);
		teamLead.handleRequest(request3);
		System.out.println();

		System.out.println("📋 ТЕСТ 4:");
		PurchaseRequest request4 = new PurchaseRequest("Коля", "Вертолёт", 500_000);
		System.out.println(request4);
		teamLead.handleRequest(request4);
	}
}