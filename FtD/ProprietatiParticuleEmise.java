public class ProprietatiParticuleEmise {

    public int NumarParticule;
    public float VitezaMin;
    public float VitezaMax;
    public int LifetimeMin;
    public int LifetimeMax;
    public float UnghiDeviere;

    public Vector2f GenereazaViteza(float Unghi) {
        float Deviere = GenereazaDeviere();
        return new Vector2f((float)Math.cos(Unghi + Deviere),(float)Math.sin(Unghi + Deviere)).MultiplicaScalar(VitezaMin + Lume.MasterRandom.nextFloat() * (VitezaMax - VitezaMin));
    }

    public float GenereazaDeviere() {
        return -UnghiDeviere + Lume.MasterRandom.nextFloat() * UnghiDeviere * 2;
    }

    public int GenereazaLifetime() {
        return LifetimeMin + (int)(Lume.MasterRandom.nextFloat() * (LifetimeMax - LifetimeMin));
    }

    public ProprietatiParticuleEmise() {

    }

    public ProprietatiParticuleEmise(int NumarParticule,float VitezaMin,float VitezaMax,int LifetimeMin,int LifetimeMax,float UnghiDeviere) {
        this.NumarParticule = NumarParticule;
        this.VitezaMin = VitezaMin;
        this.VitezaMax = VitezaMax;
        this.LifetimeMin = LifetimeMin;
        this.LifetimeMax = LifetimeMax;
        this.UnghiDeviere = UnghiDeviere;
    }

}
