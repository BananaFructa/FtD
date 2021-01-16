
public class Particula extends BaseActor {

    public int Lifetime;

    public Particula(Vector2f pos, Vector2f viteza, int Lifetime) {
        super(pos);
        this.Viteza = viteza;
        this.Lifetime = Lifetime;
    }

    @Override
    public void Update() {
        Lifetime -= 1;
    }

    public void Respawn(Vector2f pos, Vector2f Viteza,int Lifetime) {
        this.SetPozitie(pos);
        this.Viteza = Viteza;
        this.Lifetime = Lifetime;
    }
}
