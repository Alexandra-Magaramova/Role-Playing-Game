import java.util.Random;

public abstract class Gamer implements Fighting{

    private String name;
    private int health = 15; //единицы жизни
    private int money;     //золото
    private int dexterity; // ловкость
    private int skill; //опыт
    private int force; //сила
    private int level; //уровни

    public Gamer(String name, int health, int money, int dexterity, int skill, int force) {
        this.name = name;
        this.health = health;
        this.money = money;
        this.dexterity = dexterity;
        this.skill = skill;
        this.force = force;
        this.level = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level, int skill) {
        if (skill >= 15) {
            this.level ++;
            setSkill(skill - 15);
            System.out.println("Поздравляем, вы перешли на следующий уровень!");
        }
    }

    @Override
    public int attack() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if (dexterity*3 > randomNumber) return force;
        else if (dexterity - force == randomNumber) return force*2;
        else return 0;
    }

}

