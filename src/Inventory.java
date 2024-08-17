import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private List<Prize> prizes;

    public Weapon getWeapon() {
        return weapon;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public void addPrize(Prize prize) {
        prizes.add(prize);
        System.out.println(prize.getName() + " envantere eklendi.");
    }

    public void envanteriGoster() {
        for (Prize prize : this.getPrizes()) {
            System.out.print(prize);
        }
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;

    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0, 0);
        this.armor = new Armor(-1, "Paçavra", 0, 0);
        this.prizes = new ArrayList<>();
    }

}
