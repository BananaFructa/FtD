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
        Item item1=new Item(0,0);
        Item item2=new Item(0,0);
        Item item3=new Item(0,0);
        Item item4=new Item(0,0);
        Item item5=new Item(0,0);
        Item item6=new Item(0,0);
        Item item7=new Item(0,0);
        item1.setImage("fig.png");
        item2.setImage("fig.png");
        item3.setImage("fig.png");
        item4.setImage("fig.png");
        item5.setImage("fig.png");
        item6.setImage("fig.png");
        item7.setImage("fig.png");
        managerObiecte.AdaugaActor(test);
        managerObiecte.AdaugaActor(item1);
        managerObiecte.AdaugaActor(item2);
        managerObiecte.AdaugaActor(item3);
        managerObiecte.AdaugaActor(item4);
        managerObiecte.AdaugaActor(item5);
        managerObiecte.AdaugaActor(item6);
        managerObiecte.AdaugaActor(item7);
        test.addItem(item1);
        test.addItem(item2);
        test.addItem(item3);
        test.addItem(item4);
        test.addItem(item5);
        test.addItem(item6);
        test.addItem(item7);
       // System.out.println(item1.getX()+" "+item1.getY());
       // System.out.println(item2.getX()+" "+item2.getY());

        ProprietatiParticuleEmise ppe = new ProprietatiParticuleEmise();
        ppe.LifetimeMin = 20;
        ppe.LifetimeMax = 30;
        ppe.Unghi = 0;
        ppe.VitezaMin = 7;
        ppe.VitezaMax = 10;
        ppe.UnghiDeviere = 0.1f;

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
