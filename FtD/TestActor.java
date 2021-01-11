import greenfoot.Actor;

public class TestActor extends Actor {

    private int X = 0;

    public TestActor() {

    }

    @Override
    public void act() {
        this.setLocation(X++,this.getY());
    }
}
