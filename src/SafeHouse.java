public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super(player, "Güvenli ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz !");
        System.out.println("Canınız yenilendi !");
        this.getPlayer().setHealthy(this.getPlayer().getOrjinalHealthy());
        if (this.getPlayer().getInventory().isAward()){
            System.out.println("Tüm ödülleri topladınız!!!Tebrikler oyunu kazandınız....");
        }
        return true;
    }
}
