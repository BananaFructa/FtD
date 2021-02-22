import greenfoot.Greenfoot;
import greenfoot.World;

import java.io.FileNotFoundException;
import java.util.Random;

public class Lume extends World
{
    public static Lume Instanta;
    public static Random MasterRandom = new Random();

    private long SystemTime = 0;
    private long SpawnTime = 0;
    private int FpsNumarator = 999999;
    public int Fps = 0;
    public float DeltaTimp = 0;
    public final float FpsDeBaza = 60;

    public Vector2f CameraPosition;

    public ManagerObiecte managerObiecte;
    public Player player;

    public InputMouse inputMouse = new InputMouse();
    public InputKeyboard inputKeyboard = new InputKeyboard();

    public BaseActor harta;

    public boolean PlayerAObitinuteArma = false;

    public Lume()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 512, 1,false);
        this.setPaintOrder(UIActor.class,BaseActor.class);

        Instanta = this;

        Init();
    }

    public void Init() {
        managerObiecte = new ManagerObiecte();
        CameraPosition = new Vector2f(0,0);

        managerObiecte.Init();
        Interfete.Init();
        player = new Player();
        harta = new BaseActor(-100,-100);
        harta.setImage("harta.png");

        player.addItem(Items.EnergyWand);

        harta.Resize(2000,2000);

        managerObiecte.AdaugaActor(harta);
        managerObiecte.AdaugaActor(player);
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
        if (System.currentTimeMillis() - SpawnTime >= 20000) {
            SpawnTime = System.currentTimeMillis();
            managerObiecte.AdaugaActor(new InamicBall(0,100));
            if (PlayerAObitinuteArma) {
                managerObiecte.AdaugaActor(new InamicBall(0,-100));
            }
        }
        FpsNumarator++;
        if (Greenfoot.getMouseInfo() != null) {
            inputMouse.SetPos(new Vector2f((float) Greenfoot.getMouseInfo().getX(), (float) Greenfoot.getMouseInfo().getY()));
        }
        Lume.Instanta.CameraPosition = player.Pozitie.Scade(new Vector2f(this.getWidth()/2f,this.getHeight()/2f));
        if (Greenfoot.mousePressed(this)) {
            inputMouse.SetApasat(true);
            inputMouse.SetButton(Greenfoot.getMouseInfo().getButton());
            inputMouse.UIClick = false;
            inputMouse.SetObiect(null);

        }
    }
}
