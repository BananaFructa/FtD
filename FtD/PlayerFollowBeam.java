import java.util.ArrayList;
import java.util.List;

public class PlayerFollowBeam extends SistemParticule {

    List<Item> Items_ = new ArrayList<>();

    public PlayerFollowBeam (int x,int y,List<Tuple<Item,Float>> dropList) {
        super(x,y,DateParticule.PlayerFollow,0,0,-1);
        this.setImage("null.png");
        for (Tuple<Item,Float> drop : dropList) {
            if (drop.Secund() > Lume.MasterRandom.nextFloat()) {
                if (drop.Prim() == Items.CellWand) Lume.Instanta.PlayerAObitinuteArma = true;
                Items_.add(drop.Prim());
            }
        }
    }

    @Override
    public void Update() {
        super.Update();
        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null) {
            for (Item i : Items_) {
                p.addItem(i);
            }
            Lume.Instanta.managerObiecte.DisterugeActor(this);
        }
    }

    @Override
    public void act() {
        super.act();
        Player player = Lume.Instanta.player;
        if (player != null) {
            float x1 = player.Pozitie.x;
            float y1 = player.Pozitie.y;
            float x2 = this.Pozitie.x;
            float y2 = this.Pozitie.y;
            float Unghi = (float) Math.PI / 2f - (float) Math.atan((float) (x1 - x2) / (float) (y1 - y2)) + (y2 > y1 ? (float) Math.PI : 0);
            this.Viteza = new Vector2f((float) (4 * Math.cos(Unghi)), (float) (4 * Math.sin(Unghi)));
        }
    }
}
