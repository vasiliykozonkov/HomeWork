public class Main {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("   СОЗДАНИЕ ПЕРСОНАЖЕЙ В RPG");
        System.out.println("═══════════════════════════════════════\n");

        System.out.println(" ПЕРСОНАЖ 1: Минимальный");
        Character hero1 = Character.builder("Новичок", "Воин").build();
        System.out.println(hero1);

        System.out.println("📋 ПЕРСОНАЖ 2: Полная настройка");
        Character hero2 = Character.builder("Артур", "Рыцарь")
                .level(15)
                .health(500)
                .mana(100)
                .strength(30)
                .agility(15)
                .intelligence(10)
                .weapon("Экскалибур")
                .armor("Латы дракона")
                .addSkill("Удар молнии")
                .addSkill("Щит веры")
                .addSkill("Благословение")
                .pet("Дракончик")
                .build();
        System.out.println(hero2);

        System.out.println(" ПЕРСОНАЖ 3: Фабрика + кастомизация");
        Character hero3 = CharacterFactory.createMage("Гэндальф")
                .level(50)
                .addSkill("Огненный шар")
                .addSkill("Ледяная буря")
                .addSkill("Телепортация")
                .pet("Орёл")
                .build();
        System.out.println(hero3);

        System.out.println(" ПЕРСОНАЖ 4: Фабричный лучник");
        Character hero4 = CharacterFactory.createArcher("Леголас").build();
        System.out.println(hero4);

        System.out.println("📋 ПЕРСОНАЖ 5: Проверка неизменяемости");
        Character hero5 = Character.builder("Тест", "Маг")
                .health(999)
                .build();
        System.out.println("HP после создания: " + hero5.getHealth());
        System.out.println("Объект неизменяемый — изменить поля после build() нельзя!");
    }
}