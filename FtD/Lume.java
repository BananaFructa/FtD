import greenfoot.World;

public class Lume extends World
{
    public static Lume Instanta;

    private long SystemTime = 0;
    private int FpsNumarator = 999999;
    public static int Fps = 0;
    public static float DeltaTimp = 0;
    public static final float FpsDeBaza = 60;

    public static ManagerObiecte managerObiecte = new ManagerObiecte();

    public Lume()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1,false);
        Instanta = this;
        managerObiecte.Init();

        ActorTest test = new ActorTest();
        test.SetViteza(new Vector2f(0.5f,0));

        managerObiecte.AdaugaActor(test);


    }

    @Override
    public void act() {
        super.act();
        if (System.currentTimeMillis() - SystemTime >= 1000) {
            Fps = FpsNumarator;
            FpsNumarator = 0;
            DeltaTimp = FpsDeBaza/Fps;
            SystemTime = System.currentTimeMillis();
        }
        FpsNumarator++;
    }
}
