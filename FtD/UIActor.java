import greenfoot.Actor;
import greenfoot.Greenfoot;

public class UIActor extends Actor {

    protected Vector2f Pozitie;
    boolean Deschis = false;

    public UIActor(float x,float y) {
        this.Pozitie = new Vector2f(x,y);
    }

    @Override
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            Lume.Instanta.inputMouse.SetApasat(true);
        }
    }

    public void Update() {

    }

    public boolean EsteDeschis() {
        return Deschis;
    }

    public void Deschide() {

    }

    public void Inchide() {

    }

    public void Toggle() {
        this.Deschis = !this.Deschis;
        if (Deschis) Deschide();
        else Inchide();
    }

    public void Init() {
        this.setLocation((int)Pozitie.x,(int)Pozitie.y);
    }

}
