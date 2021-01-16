public class ProprietatiParticuleEmise {

    private int NumarParticule;
    private float VitezaMin;
    private float VitezaMax;
    public int LifetimeMin;
    public int LifetimeMax;
    private float Unghi;

    Vector2f GenereazaViteza() {
        return new Vector2f((float)Math.cos(Unghi),(float)Math.sin(Unghi)).MultiplicaScalar(VitezaMin + Lume.MasterRandom.nextFloat() * (VitezaMax - VitezaMin));
    }

    int GenereazaLifetime() {
        return 0;
    }

}
