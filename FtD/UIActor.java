import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

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
            Lume.Instanta.inputMouse.SetObiect(this);
            Lume.Instanta.inputMouse.UIClick = BlocheazaClick;
        }
    }

    public void Update() {

    }

    public boolean EsteDeschis() {
        return Deschis;
    }

    public void Deschide() {
        this.setLocation((int)this.Pozitie.x+ this.getImage().getWidth() / 2,(int)this.Pozitie.y+ this.getImage().getHeight() / 2);
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

    public void Resize(int w, int h) {
        this.getImage().scale(w,h);
        this.setLocation((int)this.Pozitie.x + w/2,(int)this.Pozitie.y + h/2);
    }

    public void Resize(float f) {
        float newW = this.getImage().getWidth()*f;
        float newH = this.getImage().getHeight()*f;
        this.getImage().scale((int)(newW),(int)(newH));
        this.setLocation((int)(this.Pozitie.x + newW/2),(int)(this.Pozitie.y + newH/2));
    }

    public void Toggle() {
        this.Deschis = !this.Deschis;
        if (Deschis) Deschide();
        else Inchide();
    }

    public void SetAsHolder() {
        this.setPozitie(-9999,-9999);
        this.setImage("null.png");
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
