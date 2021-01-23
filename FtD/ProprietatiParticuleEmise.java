public class ProprietatiParticuleEmise {

    public float VitezaMin;
    public float VitezaMax;
    public int LifetimeMin;
    public int LifetimeMax;
    public float Unghi;
    public float UnghiDeviere;

    public Vector2f GenereazaViteza() {
        float Deviere = GenereazaDeviere();
        return new Vector2f((float)Math.cos(Unghi + Deviere),(float)Math.sin(Unghi + Deviere)).MultiplicaScalar(VitezaMin + Lume.MasterRandom.nextFloat() * (VitezaMax - VitezaMin));
    }

    public float GenereazaDeviere() {
        return -UnghiDeviere + Lume.MasterRandom.nextFloat() * UnghiDeviere * 2;
    }

    public int GenereazaLifetime() {
        return LifetimeMin + (int)(Lume.MasterRandom.nextFloat() * (LifetimeMax - LifetimeMin));
    }

}
