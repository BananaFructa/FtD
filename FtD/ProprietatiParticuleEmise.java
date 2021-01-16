public class ProprietatiParticuleEmise {

    public float VitezaMin;
    public float VitezaMax;
    public int LifetimeMin;
    public int LifetimeMax;
    public float Unghi;

    Vector2f GenereazaViteza() {
        return new Vector2f((float)Math.cos(Unghi),(float)Math.sin(Unghi)).MultiplicaScalar(VitezaMin + Lume.MasterRandom.nextFloat() * (VitezaMax - VitezaMin));
    }

    int GenereazaLifetime() {
        return LifetimeMin + (int)(Lume.MasterRandom.nextFloat() * (LifetimeMax - LifetimeMin));
    }

}
