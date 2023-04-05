public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private String food;
    private String firewood;
    private String water;
    private String mine;

    public Inventory() {
        this.weapon=new Weapon("Yumruk",-1,0,0);
        this.armor=new Armor("Paçavra",-1,0,0);
        this.food="";
        this.firewood="";
        this.water="";
        this.mine="";
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }

    public Weapon getWeapon() {
        return weapon;
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

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFirewood() {
        return firewood;
    }

    public void setFirewood(String firewood) {
        this.firewood = firewood;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public boolean isAward(){
        if (this.getFood().equals("food") && this.getFirewood().equals("Firewood") && this.getWater().equals("Water")){
            return true;
        }
        return false;
    }
}
