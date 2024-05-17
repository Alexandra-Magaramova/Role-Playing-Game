public class Battle {
    private boolean gameEndWin = false;
    private boolean gameEndFail = false;
    private boolean gameOver = false;

    public boolean getGameEndWin() {
        return gameEndWin;
    }
    public boolean getGameEndFail() {
        return gameEndFail;
    }

    public void fight(Gamer hero, Gamer monster) throws InterruptedException {
        Runnable runnable = () -> {
            int countHit = 0;
            while (!gameOver) {
                countHit++;
                if (countHit % 2 == 0) gameOver = war(monster, hero);
                else gameOver = war(hero, monster);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
    }

    private boolean war(Gamer gamer1, Gamer gamer2){
        gameEndWin = false;
        gameEndFail = false;
        int hit = gamer1.attack();
        int damage = gamer2.getHealth() - hit;
        if (hit == 0) {
            System.out.printf("%s промахнулся.\n", gamer1.getName());
        }else {
            System.out.printf("%s нанес удар! Сила удара составила %d единиц.\nУ %s осталось %d единиц жизни\n", gamer1.getName(), hit, gamer2.getName(), damage);
        }
        if (damage <= 0){
            if (gamer2 instanceof Hero) {
                System.out.printf("К сожалению, герой %s был повержен монстром.\n", gamer2.getName());
                gameEndFail = true;
                return true;
            }else {
                System.out.printf("Ура! Герой %s одолел монстра.\nПуть чист! Можно двигаться дальше!\n", gamer1.getName());
                gamer1.setMoney(gamer1.getMoney() + gamer2.getMoney());
                gamer1.setSkill(gamer1.getSkill() + gamer2.getSkill());
                System.out.printf("Герой %s увеличил свой опыт на %d единиц и заработал %d золота \n", gamer1.getName(), gamer2.getSkill(), gamer2.getMoney());
                System.out.printf("У героя %s %d единиц здоровья, %d золота, %d опыта, %d ловкости, %d силы\n", gamer1.getName(), gamer1.getHealth(), gamer1.getMoney(), gamer1.getSkill(), gamer1.getDexterity(), gamer1.getForce());
                gameEndWin = true;
                gamer1.setLevel(gamer1.getLevel(),gamer1.getSkill());
                return true;
            }
        }else {
            gamer2.setHealth(damage);
            return false;
        }
     }
}

