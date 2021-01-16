import greenfoot.Greenfoot;

public class Player extends BaseActor{

    private final float Radical2Pe2 = (float)Math.sqrt(2)/2f;
    private int orientare;

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
                orientare=1;
            } else if (A && !D) {
                Viteza = new Vector2f(-Radical2Pe2,-Radical2Pe2);
                orientare=1;
            } else {
                Viteza = new Vector2f(0, -1);
                orientare=1;
            }
        } else if (A && !D) {
            if (S && !W) {
                Viteza = new Vector2f(-Radical2Pe2, Radical2Pe2);
                orientare=2;
            } else {
                Viteza = new Vector2f(-1, 0);
                orientare=2;
            }
        } else if (S && !W) {
            if (D && !A) {
                Viteza = new Vector2f(Radical2Pe2,Radical2Pe2);
                orientare=3;
            } else {
                Viteza = new Vector2f(0, 1);
                orientare=3;
            }
        } else if (D && !A) {
            Viteza = new Vector2f(1,0);
            orientare=4;
        }
    }
    public void direction(int s){
        switch (s){
            case 1:
                setImage("W.png");
                break;
            case 2:
                setImage("A.png");
                break;
            case 3:
                setImage("S.png");
                break;
            case 4:
                setImage("D.png");
                break;
            /*case 5:
                setImage("W_D.png");
                break;
            case 6:
                setImage("W_A.png");
                break;
            case 7:
                setImage("S_A.png");
                break;
            case 8:
                setImage("S_D.png");
                break;
                */
        }
    }
}
