import greenfoot.Actor;

public class InputMouse {

    public Vector2f Pozitie;
    public Boolean EsteApasat = false;
    public boolean UIClick = false;
    public int Button = -1;
    public Actor Obiect;

    public void SetObiect(Actor actor) {
        synchronized (this) {
            Obiect = actor;
        }
    }

    public void SetButton(int button) {
        synchronized (this) {
            Button = button;
        }
    }

    public void SetApasat(boolean stare) {
        synchronized (this) {
            EsteApasat = stare;
        }
    }

    public void SetPos(Vector2f Pos) {
        synchronized (this) {
            Pozitie =  Pos;
        }
    }

    public boolean Apasat() {
        boolean Stare;
        synchronized (this) {
            Stare = EsteApasat;
        }
        return Stare;
    }

}
