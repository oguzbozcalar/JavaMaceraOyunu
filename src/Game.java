import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);
    public boolean yemekVar = false;
    public boolean odunVar = false;
    public boolean suVar = false;

    public void start(){
        System.out.println("Macera oyununa hoşgeldiniz.");
        System.out.println("Lütfen bir isim giriniz: ");
        //String playerName = input.nextLine();
        Player player = new Player("Ogzz");
        System.out.println(player.getName() + " Hoşgeldiniz!");
        System.out.println("Lütfen bir karakter seçiniz.");
        player.selectChar();

        Location location = null;
        while(true){
            player.printInfo();
            System.out.println();
            System.out.println("############### Bölgeler ###############");
            System.out.println();
            System.out.println("1 - Güvenli Ev");
            System.out.println("2 - Eşya Dükkanı");
            System.out.println("3 - Mağara --> Ödül <Yemek>");
            System.out.println("4 - Orman --> Ödül <Odun>");
            System.out.println("5 - Nehir --> Ödül <Su>");
            System.out.println("0 - Çıkış Yap");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz:");
            int selectLoc = input.nextInt();

            for (Prize prize : player.getInventory().getPrizes()) {
                if (prize.getName().equals("Yemek") && selectLoc == 3) {
                    yemekVar = true;
                } else if (prize.getName().equals("Odun") && selectLoc == 4) {
                    odunVar = true;
                } else if (prize.getName().equals("Su") && selectLoc == 5) {
                    suVar = true;
                }
            }
            switch(selectLoc){


                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(yemekVar){
                        System.out.println("Zaten yemeğin var.Buraya tekrar giremezsin.");
                        location = new SafeHouse(player);
                    }else{
                    location = new Cave(player);}
                    break;
                case 4:
                    if(odunVar){
                        System.out.println("Zaten odunun var.Buraya tekrar giremezsin.");
                        location = new SafeHouse(player);
                    }else{
                        location = new Forest(player);}
                    break;
                case 5:
                    if(suVar){
                        System.out.println("Zaten suyun var.Buraya tekrar giremezsin.");
                        location = new SafeHouse(player);
                    }else{
                        location = new River(player);}
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz ...");


            }

            if(location == null){
                System.out.println("Oyundan çıkış yapıldı...");
                break;
            }

            if(!location.onLocation()){
                System.out.println("Game Over");
                break;
            }
        }




    }
}
