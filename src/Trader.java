public class Trader {

    private String name;  //наименование товара
    private int count; //количество оставшегося у торговца товара
    private int cost;   // стоимость товара
    private int point;  // количество единиц жизни, которые возвращает товар

    public Trader(String name, int count, int cost, int point) {
        this.name = name;
        this.count = count;
        this.cost = cost;
        this.point = point;
    }

    public boolean therapy(Gamer gamer){
        if (count > 0) {
            if (gamer.getMoney() >= cost) {
                gamer.setHealth(gamer.getHealth() + point);
                gamer.setMoney(gamer.getMoney() - cost);
                System.out.printf("Герой успешно вылечен! У него %d золота, %d единиц жизни\n", gamer.getMoney(), gamer.getHealth());
                return true;
            }else {
                System.out.println("Недостаточно золота для покупки товара\n");
                return false;
            }
        }else {
            System.out.println("К сожалению все закончилось\n");
            return false;
        }
    }
}
