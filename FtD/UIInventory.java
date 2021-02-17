import greenfoot.Actor;

import java.util.*;

public class UIInventory extends UIActor implements IContainer {

    private UIActor[] TexturiIteme = new UIActor[24];

    public UIInventory() {
        super(0, 0);
        this.setImage("background.png");
        this.getImage().scale((int)(200*2),(int)(116*2));
        for (int i = 0;i < 24;i++) {
            TexturiIteme[i] = new UIActor((int)(i%6*58.5+41),(int)(i/6*53.7+21));
            TexturiIteme[i].setImage("null.png");
        }
    }

    @Override
    public void Deschide() {
        UpdateTexturi();
        super.Deschide();
    }

    public void UpdateTexturi() {
        Item[] inv = Lume.Instanta.player.Inventar;
        for (int i = 0;i < 24;i++) {
            if (inv[i] == null) {
                TexturiIteme[i].setImage("null.png");
            } else {
                TexturiIteme[i].setImage(inv[i].tex);
                TexturiIteme[i].getImage().scale(12*2,12*2);
            }
        }
    }

    @Override
    public List<Actor> GetObiecte() {
        return new ArrayList<Actor>() {{
            addAll(Arrays.asList(TexturiIteme.clone()));
        }};
    }
}
