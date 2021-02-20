public class Proiectil extends SistemParticule implements IContainer {

    ProprietatiProiectil pp;

    public static final Object Lock = new Object();

    boolean Lovit = false;

    public Proiectil(int x,int y,float Unghi,ProprietatiParticuleEmise proprietatiParticuleEmise,ProprietatiProiectil proprietatiProiectil) {
        super(x,y,proprietatiParticuleEmise,proprietatiProiectil.Viteza,Unghi,proprietatiProiectil.Lifetime);
        pp = proprietatiProiectil;
        this.setImage(proprietatiProiectil.tex);
    }

    @Override
    public void Update() {
        super.Update();
        synchronized (Lock) {
            if (Lovit) {
                Lume.Instanta.managerObiecte.DisterugeActor(this);
            }
        }
    }

    @Override
    public void act() {
        super.act();
        synchronized (Lock) {
            if (!Lovit) {
                try {
                    Inamic i = (Inamic) getOneIntersectingObject(Inamic.class);
                    if (i != null) {
                        int x1 = (int) i.Pozitie.x;
                        int y1 = (int) i.Pozitie.y;
                        int x2 = (int) Pozitie.x;
                        int y2 = (int) Pozitie.y;
                        float Unghi = (float) Math.PI / 2f - (float) Math.atan((float) (x1 - x2) / (float) (y1 - y2)) + (y2 > y1 ? (float) Math.PI : 0);
                        i.AddFortaExterna(new SpeedComponent(new Vector2f((float) (pp.Knockback * Math.cos(Unghi)), (float) (pp.Knockback * Math.sin(Unghi))), 20));
                        i.Health -= pp.Damage;
                        Lovit = true;
                    }
                } catch (Exception err) {

                }
            }
        }
    }
}
