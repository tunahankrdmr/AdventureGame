import java.util.Scanner;

public class Game {
    private Scanner input=new Scanner(System.in);
    private BattleLoc battleLoc;

    public void start(){
        System.out.println("Oyuna Hoşgeldiniz!!!");
        System.out.println("Oyundaki isminizi giriniz : ");
        String playerName=input.next();
        Player player=new Player(playerName);
        System.out.println(playerName + " ismiyle oyun diyarına giriş yapılıyor!!");
        player.selectChar();

        Location location=null;
        while (true){
            player.printInfo();
            System.out.println("=====Bölgeler=====");
            System.out.println("1 - Güvenli ev");
            System.out.println("2 - Eşya Dükkanı");
            System.out.println("3 - Mağara");
            System.out.println("4 - Orman");
            System.out.println("5 - Nehir");
            System.out.println("6 - Maden");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçin : ");
            int selectLoc= input.nextInt();
            switch (selectLoc){
                case 0 :
                    location=null;
                    break;
                case 1 :
                    location=new SafeHouse(player);
                    break;
                case 2 :
                    location=new Toolstore(player);
                    break;
                case 3 :
                    location=new Cave(player);
                    break;
                case 4 :
                    location=new Forest(player);
                    break;
                case 5 :
                    location=new River(player);
                    break;
                case 6 :
                    location=new Mine(player);
                    break;
                default :
                    System.out.print("Lütfen geçerli bir değer giriniz : ");
            }
            if (location==null){
                System.out.println(playerName+" karakteri oyundan çıkış yapıyor!!! Tekrar bekleriz...");
                break;
            }
            if(!location.onLocation()){
                System.out.println("GAME OVER !");
                break;
            }
        }
    }
}
