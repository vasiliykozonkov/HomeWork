import java.util.ArrayList;
import java.util.List;

public class Character {

    private final String name;
    private final String characterClass;
    private final int level;
    private final int health;
    private final int mana;
    private final int strength;
    private final int agility;
    private final int intelligence;
    private final String weapon;
    private final String armor;
    private final List<String> skills;
    private final String pet;

    // Приватный конструктор — только Builder может создать объект
    private Character(Builder builder) {
        this.name = builder.name;
        this.characterClass = builder.characterClass;
        this.level = builder.level;
        this.health = builder.health;
        this.mana = builder.mana;
        this.strength = builder.strength;
        this.agility = builder.agility;
        this.intelligence = builder.intelligence;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.skills = builder.skills;
        this.pet = builder.pet;
    }

    // ✅ Статический метод для получения Builder (вместо new Character.Builder)
    public static Builder builder(String name, String characterClass) {
        return new Builder(name, characterClass);
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getPet() {
        return pet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("🧙 Персонаж: ").append(name).append("\n");
        sb.append("   Класс: ").append(characterClass).append("\n");
        sb.append("   Уровень: ").append(level).append("\n");
        sb.append("   ❤️ HP: ").append(health);
        sb.append(" | 💧 MP: ").append(mana).append("\n");
        sb.append("   ️ Сила: ").append(strength);
        sb.append(" | 🏃 Ловкость: ").append(agility);
        sb.append(" | 🧠 Интеллект: ").append(intelligence).append("\n");

        if (weapon != null) {
            sb.append("   ⚔️ Оружие: ").append(weapon).append("\n");
        }
        if (armor != null) {
            sb.append("   🛡️ Броня: ").append(armor).append("\n");
        }
        if (!skills.isEmpty()) {
            sb.append("   ✨ Навыки: ").append(skills).append("\n");
        }
        if (pet != null) {
            sb.append("   🐾 Питомец: ").append(pet).append("\n");
        }

        return sb.toString();
    }

    // Вложенный класс Builder
    public static class Builder {

        private final String name;
        private final String characterClass;

        private int level = 1;
        private int health = 100;
        private int mana = 50;
        private int strength = 10;
        private int agility = 10;
        private int intelligence = 10;
        private String weapon;
        private String armor;
        private List<String> skills = new ArrayList<>();
        private String pet;

        public Builder(String name, String characterClass) {
            this.name = name;
            this.characterClass = characterClass;
        }

        // ✅ Методы без префикса set
        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public Builder strength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder agility(int agility) {
            this.agility = agility;
            return this;
        }

        public Builder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder weapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder armor(String armor) {
            this.armor = armor;
            return this;
        }

        public Builder addSkill(String skill) {
            this.skills.add(skill);
            return this;
        }

        public Builder pet(String pet) {
            this.pet = pet;
            return this;
        }

        public Character build() {
            return new Character(this);
        }
    }
}