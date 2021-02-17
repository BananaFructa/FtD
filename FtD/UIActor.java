import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.List;

public class UIActor extends Actor {

    protected Vector2f Pozitie;
    boolean Deschis = false;
    boolean BlocheazaClick = true;

    public UIActor(float x,float y) {
        this.Pozitie = new Vector2f(x,y);
    }

    @Override
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            Lume.Instanta.inputMouse.SetApasat(true);
            Lume.Instanta.inputMouse.SetPos(new Vector2f((float)Greenfoot.getMouseInfo().getX(),(float)Greenfoot.getMouseInfo().getY()));
            Lume.Instanta.inputMouse.SetButton(Greenfoot.getMouseInfo().getButton());
            Lume.Instanta.inputMouse.UIClick = BlocheazaClick;
        }
    }

    public void Update() {

    }

    public boolean EsteDeschis() {
        return Deschis;
    }

    public void Deschide() {
        this.setLocation((int)this.Pozitie.x,(int)this.Pozitie.y);
        this.getImage().setTransparency(255);
        if (this instanceof IContainer) {
            List<UIActor> subInterfete = (List<UIActor>)(List<?>)((IContainer) this).GetObiecte();
            for (UIActor i : subInterfete) {
                i.Deschide();
            }
        }
    }

    public void Inchide() {
        this.setLocation(-9999,-9999);
        this.getImage().setTransparency(0);
        if (this instanceof IContainer) {
            List<UIActor> subInterfete = (List<UIActor>)(List<?>)((IContainer) this).GetObiecte();
            for (UIActor i : subInterfete) {
                i.Inchide();
            }
        }
    }

    public void Toggle() {
        this.Deschis = !this.Deschis;
        if (Deschis) Deschide();
        else Inchide();
    }

    public void Init() {
        this.setLocation((int)Pozitie.x,(int)Pozitie.y);
    }

    public void setPozitie(float x,float y){
        this.Pozitie.x=x;
        this.Pozitie.y=y;
        setLocation((int)Pozitie.x,(int)Pozitie.y);
    }
}
