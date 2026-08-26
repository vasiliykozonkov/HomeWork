public class Main {
	public static void main(String[] args) {
		System.out.println("═══════════════════════════════════════");
		System.out.println("   СОЗДАНИЕ ПЕРСОНАЖЕЙ В RPG");
		System.out.println("═══════════════════════════════════════\n");

		System.out.println("📋 ПЕРСОНАЖ 1: Минимальный");
		Character hero1 = new Character.Builder("Новичок", "Воин").build();
		System.out.println(hero1);

		System.out.println("📋 ПЕРСОНАЖ 2: Полная настройка");
		Character hero2 = new Character.Builder("Артур", "Рыцарь")
		.setLevel(15)
		.setHealth(500)
		.setMana(100)
		.setStrength(30)
		.setAgility(15)
		.setIntelligence(10)
		.setWeapon("Экскалибур")
		.setArmor("Латы дракона")
		.addSkill("Удар молнии")
		.addSkill("Щит веры")
		.addSkill("Благословение")
		.setPet("Дракончик")
		.build();
		System.out.println(hero2);

		System.out.println("📋 ПЕРСОНАЖ 3: Фабрика + кастомизация");
		Character hero3 = CharacterFactory.createMage("Гэндальф")
						  .setLevel(50)
						  .addSkill("Огненный шар")
						  .addSkill("Ледяная буря")
						  .addSkill("Телепортация")
						  .setPet("Орёл")
						  .build();
		System.out.println(hero3);

		System.out.println("📋 ПЕРСОНАЖ 4: Фабричный лучник");
		Character hero4 = CharacterFactory.createArcher("Леголас").build();
		System.out.println(hero4);

		System.out.println("📋 ПЕРСОНАЖ 5: Проверка неизменяемости");
		Character hero5 = new Character.Builder("Тест", "Маг")
		.setHealth(999)
		.build();
		System.out.println("До 'изменения': " + hero5.getHealth());

		System.out.println("После попытки изменить: " + hero5.getHealth() + " (не изменилось!)");
	}
}