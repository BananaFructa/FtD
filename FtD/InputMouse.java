public class InputMouse {

    public Vector2f Pozitie;
    public Boolean EsteApasat = false;
    public int Button = -1;

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

    public boolean Apasat() {
        boolean Stare;
        synchronized (this) {
            Stare = EsteApasat;
        }
        return Stare;
    }

}
