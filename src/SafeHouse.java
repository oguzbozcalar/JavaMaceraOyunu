public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz.");
        System.out.println("Canınız yenilendi.");
        this.getPlayer().setHealth(this.getPlayer().getOrjinalHealth());
        boolean yemekVar = false;
        boolean odunVar = false;
        boolean suVar = false;


        for (Prize prize : this.getPlayer().getInventory().getPrizes()) {
            if (prize.getName().equals("Yemek")) {
                yemekVar = true;
            } else if (prize.getName().equals("Odun")) {
                odunVar = true;
            } else if (prize.getName().equals("Su")) {
                suVar = true;
            }

            if (yemekVar && odunVar && suVar) {
                System.out.println("WIN !!!");
                return false;

            }



        }




        return true;
    }
}
