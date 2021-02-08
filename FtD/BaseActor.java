import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseActor extends Actor {

    protected Vector2f Pozitie;
    protected Vector2f Viteza;

    public BaseActor(float x,float y) {
        this(new Vector2f(x,y));
        Viteza = new Vector2f(0,0);
    }

    public void Update() {

    }

    public void Init() {
        this.setLocation((int)Pozitie.x,(int)Pozitie.y);
    }

    public BaseActor(Vector2f Pozitie) {
        this.Pozitie = Pozitie.Scade(Lume.Instanta.CameraPosition);
    }

    public void SetPozitie(Vector2f v) {
        this.Pozitie = v;
        this.setLocation((int)Pozitie.x,(int)Pozitie.y);
    }

    public Vector2f GetPozitie(Vector2f v) {
        return this.Pozitie;
    }

    public void SetViteza(Vector2f v) {
        this.Viteza = v;
    }

    public Vector2f GetViteza() {
        return this.Viteza;
    }

    @Override
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            Lume.Instanta.inputMouse.SetApasat(true);
            Lume.Instanta.inputMouse.SetButton(Greenfoot.getMouseInfo().getButton());
        }
        this.SetPozitie(this.Pozitie.Aduna(this.Viteza.MultiplicaScalar(Lume.Instanta.DeltaTimp)).Scade(Lume.Instanta.CameraPosition));
    }
}
