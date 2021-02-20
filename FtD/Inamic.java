import java.util.ArrayList;
import java.util.List;

public class Inamic extends BaseActor {

    public int Health;
    public float FactorViteza;
    public float Damage;
    public int AttackSpeed;

    private long TicksSinceLastAttack = 9999;

    public Inamic(int x,int y,float damange,float viteza,int Health,int AttackSpeed) {
        super(x,y);
        this.FactorViteza = viteza;
        this.Damage = damange;
        this.Health = Health;
        this.AttackSpeed = AttackSpeed;
    }

    @Override
    public void Update() {
        super.Update();
        TicksSinceLastAttack++;
        System.out.println(this.Pozitie.Distanta(Lume.Instanta.player.Pozitie));
        if (this.Pozitie.Distanta(Lume.Instanta.player.Pozitie) < 30f && TicksSinceLastAttack > AttackSpeed) {
            TicksSinceLastAttack = 0;
            Lume.Instanta.player.currentHp -= Damage;
        }
        if (Health <= 0) {
            Lume.Instanta.managerObiecte.DisterugeActor(this);
            Lume.Instanta.managerObiecte.AdaugaActor(new PlayerFollowBeam((int)this.Pozitie.x,(int)this.Pozitie.y));
        }
        UrmarestePlayer();
    }

    public void UrmarestePlayer() {
        Player player = Lume.Instanta.player;
        if (player != null) {
            float x1 = player.Pozitie.x;
            float y1 = player.Pozitie.y;
            float x2 = this.Pozitie.x;
            float y2 = this.Pozitie.y;
            float Unghi = (float) Math.PI / 2f - (float) Math.atan((float) (x1 - x2) / (float) (y1 - y2)) + (y2 > y1 ? (float) Math.PI : 0);
            this.Viteza = new Vector2f((float) (FactorViteza * Math.cos(Unghi)), (float) (FactorViteza * Math.sin(Unghi)));
        }
    }

    public List<Tuple<Item,Float>> GetDrops() {
        return new ArrayList<>();
    }
}
