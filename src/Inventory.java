import java.util.List;

public class Inventory {
   private Weapon weapon;
   private Armor armor;
   private List<Prize> prize;

    public Weapon getWeapon() {
        return weapon;
    }

    public List<Prize> getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = List.of(new Prize[]{prize});
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
       this.weapon = new Weapon("Yumruk",-1,0,0);
       this.armor = new Armor(-1,"Paçavra",0,0);
       this.prize = List.of(new Prize[]{new Prize("Boş Çanta")});
   }

}
