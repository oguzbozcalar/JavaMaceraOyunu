import java.util.Random;

public abstract class BattleLoc extends Location{

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award,int maxObstacle)  {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    boolean onLocation() {
        int obsNumber = randomObstacleNumber();
        System.out.println("Şuan buradasınız: "+this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor.");
        System.out.print("<S>avaş veya <K>aç :");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if(selectCase.equals("S") && combat(obsNumber)){
                System.out.println(this.getName() + " tüm düşmanları yendiniz.");
                String loc = this.getName();
                Prize[] prizeList = Prize.prizes();
                switch(loc){
                    case "Mağara":
                        this.getPlayer().getInventory().addPrize(prizeList[0]);
                        break;
                    case "Orman":
                        this.getPlayer().getInventory().addPrize(prizeList[1]);
                        break;
                    case "Nehir":
                        this.getPlayer().getInventory().addPrize(prizeList[2]);
                        break;
                }
                return true;
        }

        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz. ");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber){

        if (whoHitsFirst() == 1) {
            System.out.println("Kim vuracak:" +whoHitsFirst());
            for (int i = 1; i <= obsNumber; i++) {
                this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
                playerStats();
                obstacleStats(i);
                while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                    System.out.print("<V>ur veya <K>aç: ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if(selectCombat.equals("V")){
                        System.out.println("Siz vurdunuz.");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }

                    }else{
                        return false;
                    }

                }

                if(this.getObstacle().getDamage() < this.getPlayer().getHealth()){
                    System.out.println("Düşmanı yendiniz.");
                    System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                }else{
                    return false;
                }

            }
        }else{
            // Canavarın önce vurmaya başladığı senaryo
            for (int i = 1; i <= obsNumber; i++) {
                this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
                playerStats();
                obstacleStats(i);
                while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0){
                        obstacleDamage = 0;
                        }
                        System.out.println("Canavar vurdu.");
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                        afterHit();
                        if(this.getPlayer().getHealth() > 0){
                            System.out.println();
                            System.out.println("Siz vurdunuz");

                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }



                }
                if(this.getPlayer().getHealth() < this.getObstacle().getHealth()){
                    System.out.println("Düşman kazandı.No WIN no MONEY!");
                    return false;
                }

                if(this.getObstacle().getDamage() < this.getPlayer().getHealth()){
                    System.out.println("Düşmanı yendiniz.");
                    System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                }else{
                    return false;
                }

            }

        }

        return true;
    }

    public void afterHit(){
        System.out.println("Canınız: "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı :" + this.getObstacle().getHealth());
        System.out.println( );
    }

    public void obstacleStats(int i){
        System.out.println(i+" . " + this.getObstacle().getName() + " Değerleri");
        System.out.println("------------------------");
        System.out.println("Sağlık: "+ this.getObstacle().getHealth());
        System.out.println("Hasar: "+ this.getObstacle().getDamage());
        System.out.println("Ödül: "+ this.getObstacle().getAward());
        System.out.println("------------------------");
    }
    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh: "+ this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: "+ this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Para: " + this.getPlayer().getMoney());


    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public int whoHitsFirst(){
        return Math.random() > 0.5 ? 1: 2;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
