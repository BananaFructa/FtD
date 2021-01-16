import java.util.*;

public class SistemParticule extends BaseActor {

    public List<Particula> Particule = new ArrayList<Particula>();

    private int NumarParticule;
    private float VitezaMin;
    private float VitezaMax;
    private float Unghi;

    public SistemParticule(int x,int y,int NumerParticule,float VitezaMin,float VitezaMax,float Unghi,float Viteza) {
        super(x,y);
        this.NumarParticule = NumerParticule;
        this.VitezaMin = VitezaMin;
        this.VitezaMax = VitezaMax;
        this.Unghi = Unghi - (float)Math.PI;
        this.Viteza = new Vector2f((float)Math.cos(Unghi),(float)Math.sin(Unghi)).MultiplicaScalar(Viteza);
        for (int i = 0;i < NumerParticule;i += 1) {

        }
    }

    @Override
    public void Update() {
        for (Particula particula : Particule) {
            if (particula.Lifetime <= 0) {

            }
        }
    }


}
