import greenfoot.World;

public class Lume extends World
{
    public Lume()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1,false);
        addObject(new TestActor(),20,20);
    }
   
}
