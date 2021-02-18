import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.*;

public class UIInventory extends UIActor implements IContainer {

    private final UISlot[] TexturiIteme = new UISlot[24];
    UISlot CursorSlot = new UISlot(-9999,-9999,25,this);
    int cursorX = 0,cursorY = 0;

    public UIInventory() {
        super(0, 0);
        CursorSlot.SetItem(new Item());
        this.setImage("background.png");
        this.getImage().scale((int)(200*2),(int)(116*2));
        for (int i = 0;i < 24;i++) {
            TexturiIteme[i] = new UISlot((int)(i%6*58.5+41),(int)(i/6*53.7+21),i,this);
            TexturiIteme[i].SetItem(null);
        }
    }

    @Override
    public void Update() {
    }

    @Override
    public void act() {
        super.act();
        if (Deschis) {
            cursorX = (int) Lume.Instanta.inputMouse.Pozitie.x;
            cursorY = (int) Lume.Instanta.inputMouse.Pozitie.y;
            CursorSlot.setPozitie(cursorX-24, cursorY-24);
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
            TexturiIteme[i].SetItem(inv[i]);
        }
    }

    @Override
    public List<Actor> GetObiecte() {
        return new ArrayList<Actor>() {{
            addAll(Arrays.asList(TexturiIteme.clone()));
            add(CursorSlot);
        }};
    }
}
