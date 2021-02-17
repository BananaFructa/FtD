import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

import java.util.HashMap;

public class Player extends BaseActor {

    private final float Radical2Pe2 = (float) Math.sqrt(2) / 2f;
    private Directie orientare = Directie.DREAPTA;

    private Animatie MergeDreapta = new Animatie(DateAnimatii.PLAYER_WALKING_RIGHT,this);
    private Animatie MergeStanga = new Animatie(DateAnimatii.PLAYER_WALKING_LEFT,this);
    private Animatie MergeSus = new Animatie(DateAnimatii.PLAYER_WALKING_UP,this);
    private Animatie MergeJos = new Animatie(DateAnimatii.PLAYER_WALKING_DOWN,this);

    private Item hat;
    private Item robe;
    private Item boots;
    private Item wand;
    private int maxHp;
    private int currentHp;
    private int manaPoint;
    private int currentMana;
    private int manaRegen;//only by items
    private int hpRegen;//only by items
    private int lvl;
    private int def;
    private int dmg;
    private int critChance;//only by items

    private HashMap<String, Item> info =new HashMap<>();
    public Item[] Inventar = new Item[24];
    private boolean LastOpenedInventory = false;

    public Player() {
        super(0, 0);
    }

    @Override
    public void Update() {
        boolean I;
        I=Greenfoot.isKeyDown("I");

        UpdateControl();
        UpdateActiune();

        if (regenHp()) {
            currentHp += hpRegen;
        }
        if (regenMana()) {
            currentMana += manaRegen;
        }

        fixStats();

        if(I){
            setInvetoryItemTextures();
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
        } else {
            MergeStanga.Stop();
            MergeSus.Stop();
            MergeDreapta.Stop();
            if (orientare == Directie.STANGA) this.setImage("player/character_looking_left.png");
            if (orientare == Directie.DREAPTA) this.setImage("player/character_looking_right.png");
            //if (orientare == Directie.JOS) this.setImage("player/character_looking_down.png");
            if (orientare == Directie.SUS) this.setImage("player/character_looking_up.png");
        }


        if (regenHp()) {
            currentHp += hpRegen;
        }
        if (regenMana()) {
            currentMana += manaRegen;
        }

        fixStats();
        hide();
        if (Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }
    }

    public void UpdateActiune() {
        if (Lume.Instanta.inputMouse.Apasat() && !Lume.Instanta.inputMouse.UIClick) {
            int w = Lume.Instanta.getWidth() >> 1;
            int h = Lume.Instanta.getHeight() >> 1;
            int x = (int)Lume.Instanta.inputMouse.Pozitie.x;
            int y = (int)Lume.Instanta.inputMouse.Pozitie.y;
            float Unghi = (float)Math.PI/2f-(float)Math.atan((float)(w-x)/(float)(h-y)) + (y > h ? (float)Math.PI : 0);
            Lume.Instanta.managerObiecte.AdaugaActor(new Proiectil((int)Pozitie.x,(int)Pozitie.y,Unghi,DateParticule.SistemTest,DateProiectil.ProiectilTest));
        }
    }

    public void setInvetoryItemTextures() {
        String key;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                key = String.format("#%s#%s", i, j);
                if (info.containsKey(key)) {
                    Item item = info.get(key);
                    Interfete.Inventar.getItems().get(key).setTex(item);
                }
            }
        }
    }

    public BaseActor setItem(Item item){
        BaseActor ceva=new BaseActor(0,0);
        ceva.setImage("fig.png");
        return ceva;
    }
    public void setareTextureDinDirectie(Directie s) {
        switch (s) {
            case SUS:
                setImage("W.png");
                break;
            case STANGA:
                setImage("A.png");
                break;
            case JOS:
                setImage("S.png");
                break;
            case DREAPTA:
                setImage("D.png");
                break;
        }
    }

    public void hide() {
        boolean hide = Lume.Instanta.inputKeyboard.ApasatInstantaneu(InputKeyboard.Key.I);
        if(hide){
            Interfete.Inventar.Toggle();
        }
    }

    public void raiseHp() {
        maxHp += 10;
    }

    public void raiseMana() {
        manaPoint += 10;
    }

    public void raiseDmg() {
        dmg += 1;
    }

    public void raiseDef() {
        def += 1;
    }

    public boolean regenHp() {
        return (currentHp != maxHp);
    }

    public boolean regenMana() {
        return (manaPoint != currentMana);
    }

    public void fixStats() {
        if (currentHp > maxHp)
            currentHp = maxHp;
        if (currentMana > manaPoint)
            currentMana = manaPoint;
    }

    public void addItem(Item item){
        Interfete.Inventar.addItem(item);
    }

    public void dragAndDrop() {
        String key;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                key = String.format("#%s#%s", i, j);
                if (Greenfoot.mouseClicked(Interfete.Inventar))
                    System.out.println("ceva");
                if (Greenfoot.mouseDragged(Interfete.Inventar.getSlots().get(key))) {
                    System.out.println("ceva");
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    Interfete.Inventar.getItems().get(key).setPozitie(mouse.getX(), mouse.getY());
                }
            }
        }
    }

    public void equipItem(Item item) {

    }
}
