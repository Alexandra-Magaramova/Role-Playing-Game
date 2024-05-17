import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Hero hero;
    public static Goblin goblin;
    public static Skeleton skeleton;
    public static Trader trader;
    public static Scanner scanner;
    public static Battle battle;
    public static String text;
    public static boolean tradeOn = false;

    public static void main(String[] args) throws InterruptedException {
        try {
            trader = new Trader("зелье", 1,2,10);
            System.out.println("Введите имя героя:");
            scanner = new Scanner(System.in);
            game(scanner.nextLine());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //вывод меню
    public static void enterMenu(){
        System.out.println("Куда вы хотите пойти?\n" +
                "1. К торговцу \n" +
                "2. В темный лес \n" +
                "3. На выход");
    }

    //логика игры
    public static void game (String text) throws InterruptedException {
        try {
            if (hero == null) {
               hero = new Hero(text, 10, 10, 10, 10, 10);
               System.out.printf("Добро пожаловать в волшебный мир, %s!\n", text);
               enterMenu();
               text = scanner.nextLine();
           }
           switch (text) {
               case "1": {
                   tradeOn = true;
                   trader.therapy(hero);
                   System.out.println("Продолжить торговлю или вернуться в город?");
                   question(scanner.nextLine());
               }
               break;
               case "2": {
                   tradeOn = false;
                   System.out.println("Сегодня ты встретишься с монстром! \nЧтобы пройти через лес, нужно одолеть монстра! Удачи в бою!");
                   battle = new Battle();
                   battle.fight(hero, chooseMonster());
                   if (battle.getGameEndWin()) {
                       if (hero.getLevel() == 5) {
                           System.out.println("Поздравляем! Вы выиграли!");
                           game("3");
                       }else {
                           System.out.println("Продолжить поход или вернуться в город?");
                           question(scanner.nextLine());
                       }
                   } else if (battle.getGameEndFail()) game("3");
               }
               break;
               case "3":
                   System.out.println("Игра закончена");
                   break;
               default: {
                   System.out.println("Некорректный ввод. Попробуйте снова");
                   game(scanner.nextLine());
               }
           }
       } catch (InterruptedException e) {
               e.printStackTrace();
       }
    }
    //обработка да/нет для продолжения игры
    public static void question(String text) throws InterruptedException {
        try {
            switch (text){
                case "да": {
                    if (tradeOn) game("1");
                    else game("2");
                }
                break;
                case "нет": {
                    enterMenu();
                    game(scanner.nextLine());
                }
                break;
                default: {
                    System.out.println("Некорректная команда. Попробуйте снова");
                    question(scanner.nextLine());
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //выбор монстра
    public static Gamer chooseMonster (){
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 0) {
            goblin = new Goblin("Goba", 20, 20, 20, 20, 20);
            System.out.println("Вам достался гоблин");
            return goblin;
        }
        else {
            skeleton = new Skeleton("Skelet", 5, 5, 5, 5, 5);
            System.out.println("Вам достался скелет");
            return skeleton;
        }
    }
}
