import java.util.Random;

public class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Random r=new Random();
    public BattleLoc(Player player, String name,Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle=obstacle;
        this.award=award;
        this.maxObstacle=maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber=this.ramdomObstacleNumber();
        if (this.getObstacle().getName().equals("Zombi") && this.getPlayer().getInventory().getFood().equals("food")){
            System.out.println("Mağaradaki ödülü kazandınız..Sonraki savaş alanına gidin!!!");
            return true;
        }else if (this.getObstacle().getName().equals("Vampir") && this.getPlayer().getInventory().getFirewood().equals("Firewood")){
            System.out.println("Ormandaki ödülü kazandınız..Sonraki savaş alanına gidin!!!");
            return true;
        }else if (this.getObstacle().getName().equals("Ayı") && this.getPlayer().getInventory().getWater().equals("Water")){
            System.out.println("Nehirdeki ödülü kazandınız..Sonraki savaş alanına gidin!!!");
            return true;
        }
        System.out.println("Şuan buradasınız : "+this.getName());
        System.out.println("Dikkatli ol burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor!!");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase=input.next().toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)){
                System.out.println(this.getName() + " tüm düşmanları yendiniz!!");
                locationAward(this.getAward());
                return true;
        }
        if (this.getPlayer().getHealthy()<=0){
            System.out.println("Öldünüz!!!");
            return false;
        }
        return true;
    }

    public boolean locationAward(String award){
        if (award.equals("food")){
            this.getPlayer().getInventory().setFood(award);
        }else if (award.equals("Firewood")){
            this.getPlayer().getInventory().setFirewood(award);
        }else if (award.equals("Water")){
            this.getPlayer().getInventory().setWater(award);
        }else if (award.equals("Maden")){
            this.getPlayer().getInventory().setMine(award);
        }
        return true;
    }

    public boolean combat(int obsNumber){
        String selectCombat="";
        for (int i=1; i<=obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            if (this.getObstacle().getName().equals("Yılan")){
                randomDamage();
            }
            playerStats();
            obstacleStats(i);
            if (this.getPlayer().getHealthy()>0 && this.getObstacle().getHealth()>0){
                if (randomHit()){
                    System.out.print("<V>ur veya <K>aç : ");
                    selectCombat=input.next().toUpperCase();
                    if (selectCombat.equals("V")){
                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                }else if (this.getObstacle().getHealth()>0){
                    System.out.println(this.getObstacle().getName() + " size vurdu.");
                    int obstacleDamage=this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                    if (obstacleDamage<0){
                        obstacleDamage=0;
                    }
                    this.getPlayer().setHealthy(this.getPlayer().getHealthy()-obstacleDamage);
                    afterHit();
                }
            }
            while (this.getPlayer().getHealthy()>0 && this.getObstacle().getHealth()>0){
                if (selectCombat.equals("K")){
                    break;
                }
                System.out.print("<V>ur veya <K>aç : ");
                selectCombat=input.next().toUpperCase();
                if (selectCombat.equals("V")){
                    System.out.println("Siz vurdunuz!");
                    this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth()>0){
                        System.out.println(this.getObstacle().getName() + " size vurdu.");
                        int obstacleDamage=this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage<0){
                            obstacleDamage=0;
                        }
                        this.getPlayer().setHealthy(this.getPlayer().getHealthy()-obstacleDamage);
                        afterHit();
                    }
                }else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth()<this.getPlayer().getHealthy()){
                System.out.println("Düşmanı Yendiniz!!");
                if (this.getObstacle().getName().equals("Yılan")){
                    randomAward();
                }
                System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            }else {
                return false;
            }
        }
        return  true;
    }

    public void randomDamage() {
        Random random = new Random();
        int randomHit = random.nextInt(3) + 3;
        this.getObstacle().setDamage(randomHit);
    }

    public void randomAward(){
        int randomValue = r.nextInt(100);
        if (randomValue<15){
            weaponAward();
        }else if (randomValue<30){
            armorAward();
        }else if (randomValue<55){
            moneyAward();
        }else {
            System.out.println("Canavardan ganimet düşmedi!!");
        }
    }

    public void weaponAward() {
        if (this.getObstacle().getHealth() == 0) {
            int randomValue = r.nextInt(100);
            if (randomValue < 20) {
                System.out.println("Tüfek kazandınız!!");
                this.getPlayer().getInventory().getWeapon().setDamage(this.getPlayer().getInventory().getWeapon().getDamage() + 7);
                this.getPlayer().getInventory().getWeapon().setName("Tüfek");
            } else if (randomValue < 50) {
                System.out.println("Kılıç kazandınız!!");
                this.getPlayer().getInventory().getWeapon().setDamage(this.getPlayer().getInventory().getWeapon().getDamage() + 5);
                this.getPlayer().getInventory().getWeapon().setName("Kılıç");
            } else {
                System.out.println("Tabanca kazandınız!!");
                this.getPlayer().getInventory().getWeapon().setDamage(this.getPlayer().getInventory().getWeapon().getDamage() + 3);
                this.getPlayer().getInventory().getWeapon().setName("Tabanca");
            }
        }
    }

    public void armorAward() {
        if (this.getObstacle().getHealth() == 0) {
            int randomValue = r.nextInt(100);
            if (randomValue < 20) {
                System.out.println("Ağır zırh kazandınız!!");
                this.getPlayer().getInventory().getArmor().setBlock(this.getPlayer().getInventory().getArmor().getBlock() + 5);
                this.getPlayer().getInventory().getArmor().setName("Ağır");
            } else if (randomValue < 50) {
                System.out.println("Orta zırh kazandınız!!");
                this.getPlayer().getInventory().getArmor().setBlock(this.getPlayer().getInventory().getArmor().getBlock() + 3);
                this.getPlayer().getInventory().getArmor().setName("Orta");
            } else {
                System.out.println("Hafif zırh kazandınız!!");
                this.getPlayer().getInventory().getArmor().setBlock(this.getPlayer().getInventory().getArmor().getBlock() + 1);
                this.getPlayer().getInventory().getArmor().setName("Hafif");
            }
        }
    }
    public void moneyAward(){
        if (this.getObstacle().getHealth() == 0) {
            int randomValue = r.nextInt(100);
            if (randomValue < 20) {
                System.out.println("Canavardan 10 para kazandınız!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
            } else if (randomValue < 50) {
                System.out.println("Canavardan 5 para kazandınız!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
            } else {
                System.out.println("Canavardan 1 para kazandınız!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
            }
        }
    }

    public boolean randomHit(){
        double randomValue=Math.random()*2;
        return randomValue<=1;
    }

    public void afterHit(){
        System.out.println("Canınız : " +this.getPlayer().getHealthy());
        System.out.println(this.getObstacle().getName() + " Canı : " +this.getObstacle().getHealth());
        System.out.println("-------------------------");
    }

    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("-------------------------");
        System.out.println("Sağlık : " +this.getPlayer().getHealthy());
        System.out.println("Silah : " +this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " +this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " +this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " +this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " +this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStats(int i){
        System.out.println(i + " . " + this.getObstacle().getName() + " Değerleri");
        System.out.println("-------------------------");
        System.out.println("Sağlık : " +this.getObstacle().getHealth());
        System.out.println("Hasar : " +this.getObstacle().getDamage());
        System.out.println("Ödül : " +this.getObstacle().getAward());
        System.out.println();
    }

    public int ramdomObstacleNumber(){
        Random r=new Random();
        return r.nextInt(3)+1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
