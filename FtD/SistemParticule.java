import java.util.*;

public class SistemParticule extends BaseActor {

    public List<Particula> Particule = new ArrayList<Particula>();

    private int NumarParticule;
    ProprietatiParticuleEmise proprietatiParticuleEmise;

    public SistemParticule(int x,int y,int NumarParticule,ProprietatiParticuleEmise proprietatiParticuleEmise,float Viteza) {
        super(x,y);
        this.proprietatiParticuleEmise = proprietatiParticuleEmise;
        this.Viteza = new Vector2f((float)Math.cos(proprietatiParticuleEmise.Unghi - Math.PI),(float)Math.sin(proprietatiParticuleEmise.Unghi - Math.PI)).MultiplicaScalar(Viteza);
        this.NumarParticule = NumarParticule;
        for (int i = 0;i < NumarParticule;i += 1) {
            Particula p = new Particula(this.Pozitie,proprietatiParticuleEmise.GenereazaViteza(),proprietatiParticuleEmise.GenereazaLifetime());
            Lume.Instanta.managerObiecte.AdaugaActor(p);
            Particule.add(p);
        }
    }

    @Override
    public void Update() {
        for (Particula particula : Particule) {
            if (particula.Lifetime <= 0) {
                particula.Respawn(this.Pozitie,proprietatiParticuleEmise.GenereazaViteza(),proprietatiParticuleEmise.GenereazaLifetime());
            }
        }
    }
}
