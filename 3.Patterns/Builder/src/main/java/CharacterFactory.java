public class CharacterFactory {

    public static Character.Builder createWarrior(String name) {
        return Character.builder(name, "Воин")
                .health(200)
                .mana(30)
                .strength(20)
                .agility(10)
                .intelligence(5)
                .weapon("Меч")
                .armor("Кольчуга");
    }

    public static Character.Builder createMage(String name) {
        return Character.builder(name, "Маг")
                .health(80)
                .mana(200)
                .strength(5)
                .agility(10)
                .intelligence(25)
                .weapon("Посох")
                .armor("Мантия");
    }

    public static Character.Builder createArcher(String name) {
        return Character.builder(name, "Лучник")
                .health(120)
                .mana(60)
                .strength(10)
                .agility(25)
                .intelligence(10)
                .weapon("Лук")
                .armor("Кожаная броня");
    }
}