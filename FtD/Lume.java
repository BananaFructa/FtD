import greenfoot.Greenfoot;
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

    public Vector2f CameraPosition = new Vector2f(0,0);

    public ManagerObiecte managerObiecte = new ManagerObiecte();
    public Player player;

    public InputMouse inputMouse = new InputMouse();
    public InputKeyboard inputKeyboard = new InputKeyboard();

    public Lume()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 512, 1,false);
        this.setPaintOrder(UIActor.class,BaseActor.class);

        Instanta = this;

        managerObiecte.Init();
        Interfete.Init();

        Item item1=new Item();
        Item item2=new Item();
        Item item3=new Item();
        Item item4=new Item();
        Item item5=new Item();
        Item item6=new Item();
        Item item7=new Item();
       // System.out.println(item1.getX()+" "+item1.getY());
       // System.out.println(item2.getX()+" "+item2.getY());
        player = new Player();
        player.SetViteza(new Vector2f(0.5f,0));
        player.addItem(item1);
        player.addItem(item2);
        player.addItem(item3);
        player.addItem(item4);
        player.addItem(item5);
        player.addItem(item6);
        player.addItem(item7);

        managerObiecte.AdaugaActor(player);

        managerObiecte.AdaugaActor(new SistemParticule(40,40,DateParticule.SistemTest,0,0,-1));

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
        Lume.Instanta.CameraPosition = player.Pozitie.Scade(new Vector2f(this.getWidth()/2f,this.getHeight()/2f));
        if (Greenfoot.mousePressed(this)) {
            inputMouse.SetApasat(true);
            inputMouse.SetPos(new Vector2f((float)Greenfoot.getMouseInfo().getX(),(float)Greenfoot.getMouseInfo().getY()));
            inputMouse.SetButton(Greenfoot.getMouseInfo().getButton());
            Lume.Instanta.inputMouse.UIClick = false;
        }
    }
}
