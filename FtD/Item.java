import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends BaseActor
{
    /**
     * Act - do whatever the Item wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int dmg=0;
    private int manaDrain=0;
    private int manaPoint=0;
    private int defPoint=0;
    private int lvl=0;
    private int manaRegenPoint=0;
    private int hpRegenPoint=0;
    private int criticalChance=0;
    private int hpGain;
    private int manaGain;

    public Item(float x,float y){
        super(x, y);
    }
    public void act() 
    {
        // Add your action code here.
    }
    public void generateStat(){
        dmg*=lvl;
        manaDrain*=lvl;
        manaPoint*=lvl;
        defPoint*=lvl;
        manaRegenPoint*=lvl;
        criticalChance*=lvl;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public int getDefPoint() {
        return defPoint;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHpGain() {
        return hpGain;
    }

    public int getHpRegenPoint() {
        return hpRegenPoint;
    }

    public int getLvl() {
        return lvl;
    }

    public int getManaDrain() {
        return manaDrain;
    }

    public int getManaGain() {
        return manaGain;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getManaRegenPoint() {
        return manaRegenPoint;
    }
}
