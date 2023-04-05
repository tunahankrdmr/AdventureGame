import java.util.Scanner;

public class Player{
    private int damage;
    private int healthy;
    private int money;
    private String name;
    private String charName;
    private Scanner input=new Scanner(System.in);
    private Inventory inventory;
    private int orjinalHealthy;


    public Player(String name) {
        this.name = name;
        this.inventory=new Inventory();
        this.orjinalHealthy=healthy;
    }

    public void selectChar(){
        System.out.println("===========================");
        GameChar[] charList={new Samurai(), new Archer(), new Knight()};
        for (GameChar character : charList){
            System.out.println("Karakter:"+character.getName()+
                    "\t\t ID:"+character.getId()+
                    "\t\t Hasar:"+character.getDamage()+
                    "\t\t Sağlık:"+character.getHealthy()+
                    "\t\t Para:"+character.getMoney());
        }
        System.out.println("==========================");
        System.out.println("Karakterinizi seçiniz : ");
        int selectId= input.nextInt();
        switch (selectId){
            case 1 :
                initPlayer(new Samurai());
                break;
            case 2 :
                initPlayer(new Archer());
                break;
            case 3 :
                initPlayer(new Knight());
                break;
            default :
                initPlayer(new Knight());
        }
    }

    public void initPlayer(GameChar gameChar){
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setOrjinalHealthy(gameChar.getHealthy());
        this.setHealthy(gameChar.getHealthy());
        this.setMoney(gameChar.getMoney());
    }

    public void printInfo(){
        System.out.println("Silahınız:"+this.getInventory().getWeapon().getName()+
                "\t\t Zırh:"+this.getInventory().getArmor().getName()+
                "\t\t Engelleme:"+this.getInventory().getArmor().getBlock()+
                "\t\t Hasar:"+this.getTotalDamage()+
                "\t\t Sağlık:"+this.getHealthy()+
                "\t\t Para:"+this.getMoney()+
                "\t\t Yemek:"+this.getInventory().getFood()+
                "\t\t Odun:"+this.getInventory().getFirewood()+
                "\t\t Su:"+this.getInventory().getWater());
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        if (healthy<0){
            healthy=0;
        }
        this.healthy = healthy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOrjinalHealthy() {
        return orjinalHealthy;
    }

    public void setOrjinalHealthy(int orjinalHealthy) {
        this.orjinalHealthy = orjinalHealthy;
    }
}
