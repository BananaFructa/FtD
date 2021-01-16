import greenfoot.Greenfoot;

public class Player extends BaseActor{

    private final float Radical2Pe2 = (float)Math.sqrt(2)/2f;

    public Player() {
        super(0,0);
    }

    @Override
    public void Update() {
        Viteza = new Vector2f();
        boolean W,A,S,D;
        W = Greenfoot.isKeyDown("W");
        A = Greenfoot.isKeyDown("A");
        S = Greenfoot.isKeyDown("S");
        D = Greenfoot.isKeyDown("D");

        if (W && !S) {
            if (D && !A) {
                Viteza = new Vector2f(Radical2Pe2,-Radical2Pe2);
            } else if (A && !D) {
                Viteza = new Vector2f(-Radical2Pe2,-Radical2Pe2);
            } else {
                Viteza = new Vector2f(0, -1);
            }
        } else if (A && !D) {
            if (S && !W) {
                Viteza = new Vector2f(-Radical2Pe2, Radical2Pe2);
            } else {
                Viteza = new Vector2f(-1, 0);
            }
        } else if (S && !W) {
            if (D && !A) {
                Viteza = new Vector2f(Radical2Pe2,Radical2Pe2);
            } else {
                Viteza = new Vector2f(0, 1);
            }
        } else if (D && !A) {
            Viteza = new Vector2f(1,0);
        }
    }
}
