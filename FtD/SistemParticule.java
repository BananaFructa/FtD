import greenfoot.Actor;

import java.util.*;

public class SistemParticule extends BaseActor implements IContainer {

    public List<Particula> Particule = new ArrayList<Particula>();

    ProprietatiParticuleEmise proprietatiParticuleEmise;
    private int Lifetime;
    private int NumarParticule;
    private float Unghi;

    public SistemParticule(int x,int y,ProprietatiParticuleEmise proprietatiParticuleEmise,float Viteza,float Unghi,int Lifetime) {
        super(x,y);
        this.Unghi = Unghi;
        this.proprietatiParticuleEmise = proprietatiParticuleEmise;
        this.NumarParticule = proprietatiParticuleEmise.NumarParticule;
        this.Viteza = new Vector2f((float)Math.cos(Unghi - Math.PI),(float)Math.sin(Unghi - Math.PI)).MultiplicaScalar(Viteza);
        for (int i = 0;i < NumarParticule;i += 1) {
            Particula p = new Particula(this.Pozitie,proprietatiParticuleEmise.GenereazaViteza(Unghi),proprietatiParticuleEmise.GenereazaLifetime());
            Particule.add(p);
        }
        this.Lifetime = Lifetime;
    }

    @Override
    public void Update() {
        for (Particula particula : Particule) {
            if (particula.Lifetime <= 0) {
                particula.Respawn(this.Pozitie,proprietatiParticuleEmise.GenereazaViteza(Unghi),proprietatiParticuleEmise.GenereazaLifetime());
            }
        }
        Lifetime--;
        if (Lifetime == 0) {
            Lume.Instanta.managerObiecte.DisterugeActor(this);
        }
    }

    @Override
    public List<Actor> GetObiecte() {
        return (List<Actor>)(List<?>)Particule;
    }
}
