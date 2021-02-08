import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Player extends BaseActor implements IContainer{

    private final float Radical2Pe2 = (float) Math.sqrt(2) / 2f;
    private Directie orientare;
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
    private Inventory iny;

    public Player() {
        super(0, 0);
        this.setImage("bruh.jpg");
        iny=new Inventory();
    }

    @Override
    public void Update() {
        Viteza = new Vector2f();
        boolean W, A, S, D,I;
        W = Greenfoot.isKeyDown("W");
        A = Greenfoot.isKeyDown("A");
        S = Greenfoot.isKeyDown("S");
        D = Greenfoot.isKeyDown("D");

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
        } else if (A && !D) {
            if (S && !W) {
                Viteza = new Vector2f(-Radical2Pe2, Radical2Pe2);
            } else {
                Viteza = new Vector2f(-1, 0);
            }
            orientare = Directie.STANGA;
        } else if (S && !W) {
            if (D && !A) {
                Viteza = new Vector2f(Radical2Pe2, Radical2Pe2);
                orientare = Directie.DREAPTA;
            } else {
                Viteza = new Vector2f(0, 1);
                orientare = Directie.JOS;
            }
        } else if (D && !A) {
            Viteza = new Vector2f(1, 0);
            orientare = Directie.DREAPTA;
        }
        if (regenHp()) {
            currentHp += hpRegen;
        }
        if (regenMana()) {
            currentMana += manaRegen;
        }
        fixStats();
        hide();
        if (Greenfoot.mouseDragged(this)){
            MouseInfo mouse=Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }
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
            /*case 5:
                setImage("W_D.png");
                break;
            case 6:
                setImage("W_A.png");
                break;
            case 7:
                setImage("S_A.png");
                break;
            case 8:
                setImage("S_D.png");
                break;
                */
        }
    }
    public void hide(){
        String key= Greenfoot.getKey();
        if("i".equals(key)){
            iny.setOpen(!iny.getOpen());
            iny.hide(iny.getOpen());
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

    @Override
    public List<BaseActor> GetObiecte() {
        return new ArrayList<>(){{add(iny);}};
    }
    public void addItem(Item item){
        iny.addItem(item);
    }
    public void dragAndDrop(){
        String key;
        for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
                key=String.format("#%s#%s",i,j);
                if(Greenfoot.mouseClicked(iny))
                    System.out.println("ceva");
                if(Greenfoot.mouseDragged(iny.getSlots().get(key))){
                    System.out.println("ceva");
                    MouseInfo mouse=Greenfoot.getMouseInfo();
                    iny.getSlots().get(key).getTex().setLocation(mouse.getX(),mouse.getY());
                }
            }
        }
    }
}
