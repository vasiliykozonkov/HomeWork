public class CharacterFactory {

	public static Character.Builder createWarrior(String name) {
		return new Character.Builder(name, "Воин")
			   .setHealth(200)
			   .setMana(30)
			   .setStrength(20)
			   .setAgility(10)
			   .setIntelligence(5)
			   .setWeapon("Меч")
			   .setArmor("Кольчуга");
	}

	public static Character.Builder createMage(String name) {
		return new Character.Builder(name, "Маг")
			   .setHealth(80)
			   .setMana(200)
			   .setStrength(5)
			   .setAgility(10)
			   .setIntelligence(25)
			   .setWeapon("Посох")
			   .setArmor("Мантия");
	}

	public static Character.Builder createArcher(String name) {
		return new Character.Builder(name, "Лучник")
			   .setHealth(120)
			   .setMana(60)
			   .setStrength(10)
			   .setAgility(25)
			   .setIntelligence(10)
			   .setWeapon("Лук")
			   .setArmor("Кожаная броня");
	}
}