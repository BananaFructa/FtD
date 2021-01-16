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
    private int staminaDrain=0;
    private int magicDmg=0;
    private int manaDrain=0;
    private int manaPoint=0;
    private int strPoint=0;
    private int hpPoint=0;
    private int defPoint=0;
    private int magicDefPoint=0;
    private int Lvl=0;
    private int staminaPoint=0;
    private int manaRegenPoint=0;
    private int hpRegenPoint=0;
    private int staminaRegenPoint=0;

    public Item(float x,float y){
        super(x, y);

    }
    public void act() 
    {
        // Add your action code here.
    }    
}
