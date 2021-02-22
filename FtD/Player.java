import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

import java.util.HashMap;

public class Player extends BaseActor {

    private final float Radical2Pe2 = (float) Math.sqrt(2) / 2f;
    private Directie orientare = Directie.DREAPTA;

    private long TicksSinceLastShoot = 9999;
    private long TicksSinceLastHPRegen = 9999;

    private Animatie MergeDreapta = new Animatie(DateAnimatii.PLAYER_WALKING_RIGHT,this);
    private Animatie MergeStanga = new Animatie(DateAnimatii.PLAYER_WALKING_LEFT,this);
    private Animatie MergeSus = new Animatie(DateAnimatii.PLAYER_WALKING_UP,this);
    private Animatie MergeJos = new Animatie(DateAnimatii.PLAYER_WALKING_DOWN,this);

    public int maxHp = 100;
    public int maxMp = 200;
    public int currentHp = 100;
    public int currentMana = 100;

    private HashMap<String, Item> info =new HashMap<>();

    public Item[] Inventar = new Item[24];

    private boolean LastOpenedInventory = false;

    public Player() {
        super(0, 0);
    }

    @Override
    public void Update() {
        super.Update();

        TicksSinceLastShoot++;
        TicksSinceLastHPRegen++;

        boolean I;
        I=Greenfoot.isKeyDown("I");

        UpdateControl();
        UpdateActiune();

        if (regenHp() && TicksSinceLastHPRegen > 3) {
            currentHp += 1;
            TicksSinceLastHPRegen = 0;
        }
        if (regenMana()) {
            currentMana += 1;
        }

        if (currentHp <= 0) {
            SetPozitie(new Vector2f(0,0));
            currentMana = maxMp/2;
            currentHp = maxHp;
            Lume.Instanta.managerObiecte.RemoveAllOf(Inamic.class);
        }

        fixStats();

        if(I){
            Interfete.Inventar.Toggle();
        }
    }

    public void UpdateControl() {
        Viteza = new Vector2f();
        boolean W, A, S, D;
        W = Lume.Instanta.inputKeyboard.EsteApasat(InputKeyboard.Key.W);
        A = Lume.Instanta.inputKeyboard.EsteApasat(InputKeyboard.Key.A);
        S = Lume.Instanta.inputKeyboard.EsteApasat(InputKeyboard.Key.S);
        D = Lume.Instanta.inputKeyboard.EsteApasat(InputKeyboard.Key.D);

        boolean merge = false;

        if (W && !S) {
            if (D && !A) {
                Viteza = new Vector2f(Radical2Pe2, -Radical2Pe2);
                orientare = Directie.DREAPTA;
            } else if (A && !D) {
                Viteza = new Vector2f(-Radical2Pe2, -Radical2Pe2);
                orientare = Directie.STANGA;
            } else {
                Viteza = new Vector2f(0, -1);
                orientare = Directie.SUS;
            }
            merge = true;
        } else if (A && !D) {
            if (S && !W) {
                Viteza = new Vector2f(-Radical2Pe2, Radical2Pe2);
            } else {
                Viteza = new Vector2f(-1, 0);
            }
            orientare = Directie.STANGA;
            merge = true;
        } else if (S && !W) {
            if (D && !A) {
                Viteza = new Vector2f(Radical2Pe2, Radical2Pe2);
                orientare = Directie.DREAPTA;
            } else {
                Viteza = new Vector2f(0, 1);
                orientare = Directie.JOS;
            }
            merge = true;
        } else if (D && !A) {
            Viteza = new Vector2f(1, 0);
            orientare = Directie.DREAPTA;
            merge = true;
        }

        if (merge) {
            if (orientare == Directie.STANGA) MergeStanga.Start();
            else MergeStanga.Stop();

            if (orientare == Directie.DREAPTA) MergeDreapta.Start();
            else MergeDreapta.Stop();

            if (orientare == Directie.SUS) MergeSus.Start();
            else MergeSus.Stop();

            if (orientare == Directie.JOS) MergeJos.Start();
            else MergeJos.Stop();

        } else {
            MergeStanga.Stop();
            MergeSus.Stop();
            MergeDreapta.Stop();
            MergeJos.Stop();
            if (orientare == Directie.STANGA) this.setImage("player/character_looking_left.png");
            if (orientare == Directie.DREAPTA) this.setImage("player/character_looking_right.png");
            if (orientare == Directie.JOS) this.setImage("player/character_looking_down.png");
            if (orientare == Directie.SUS) this.setImage("player/character_looking_up.png");
        }

        if(Lume.Instanta.inputKeyboard.ApasatInstantaneu(InputKeyboard.Key.I)){
            Interfete.Inventar.Toggle();
        }

        if (Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }
    }

    public void UpdateActiune() {
        if (Lume.Instanta.inputMouse.Apasat() && !Lume.Instanta.inputMouse.UIClick) {
            if (Inventar[0] != null && Inventar[0].AttackSpeed < TicksSinceLastShoot && currentMana >= Inventar[0].ManaCost && Inventar[0].Attacks) {
                TicksSinceLastShoot = 0;
                currentMana -= Inventar[0].ManaCost;
                int w = Lume.Instanta.getWidth() >> 1;
                int h = Lume.Instanta.getHeight() >> 1;
                int x = (int) Lume.Instanta.inputMouse.Pozitie.x;
                int y = (int) Lume.Instanta.inputMouse.Pozitie.y;
                float Unghi = (float) Math.PI / 2f - (float) Math.atan((float) (w - x) / (float) (h - y)) + (y > h ? (float) Math.PI : 0);
                Lume.Instanta.managerObiecte.AdaugaActor(new Proiectil((int) Pozitie.x, (int) Pozitie.y, Unghi, Inventar[0].ppe, Inventar[0].pp));
            }
        }
    }

    @Override
    public void SetPozitie(Vector2f v) {
        synchronized (this) {
            super.SetPozitie(v);
        }
    }

    public void SetItem(int idx, Item item) {
        this.Inventar[idx] = item;
    }

    public boolean regenHp() {
        return (currentHp != maxHp);
    }

    public boolean regenMana() {
        return (maxMp != currentMana);
    }

    public void fixStats() {
        if (currentHp > maxHp)
            currentHp = maxHp;
        if (currentMana > maxMp)
            currentMana = maxMp;
    }

    public boolean addItem(Item item){
        for (int i =0;i < 24;i++) {
            if (Inventar[i] == null) {
                Inventar[i] = item.clone();
                if(Interfete.Inventar.Deschis) {
                    Interfete.Inventar.UpdateTexturi();
                }
                return true;
            }
        }
        return false;
    }

    public void equipItem(Item item) {

    }
}
