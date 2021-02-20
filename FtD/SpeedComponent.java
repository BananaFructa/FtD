public class SpeedComponent {
    public Vector2f viteza;
    public int Lifetime;
    public BaseActor parent;

    public SpeedComponent(Vector2f viteza,int Lifetime) {
        this.viteza = viteza;
        this.Lifetime = Lifetime;
    }

    public void Update(){
        Lifetime--;
        if (Lifetime <= 0) {
            parent.EliminaFortaExterna(this);
        }
    }


    public Vector2f AplicaPeVecotr(Vector2f v) {
        return v.Aduna(viteza);
    }
}
