import greenfoot.World;

import java.util.Random;

public class Lume extends World
{
    public static Lume Instanta;
    public static Random MasterRandom = new Random();

    private long SystemTime = 0;
    private int FpsNumarator = 999999;
    public int Fps = 0;
    public float DeltaTimp = 0;
    public final float FpsDeBaza = 60;

    public ManagerObiecte managerObiecte = new ManagerObiecte();

    public Lume()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1,false);
        Instanta = this;
        managerObiecte.Init();

        Player test = new Player();
        test.SetViteza(new Vector2f(0.5f,0));

        managerObiecte.AdaugaActor(test);

        ProprietatiParticuleEmise ppe = new ProprietatiParticuleEmise();
        ppe.LifetimeMin = 20;
        ppe.LifetimeMax = 30;
        ppe.Unghi = 0;
        ppe.VitezaMin = 0.3f;
        ppe.VitezaMax = 1;

        managerObiecte.AdaugaActor(new SistemParticule(40,40,100,ppe,0));

    }

    @Override
    public void act() {
        super.act();
        if (System.currentTimeMillis() - SystemTime >= 100) {
            Fps = FpsNumarator * 10;
            FpsNumarator = 0;
            DeltaTimp = FpsDeBaza/Fps;
            SystemTime = System.currentTimeMillis();
        }
        FpsNumarator++;
    }
}
