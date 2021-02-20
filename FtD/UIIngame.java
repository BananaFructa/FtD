import greenfoot.Actor;

import java.util.ArrayList;
import java.util.List;

public class UIIngame extends  UIActor implements IContainer {

    private final UIActor stats;
    private final UIActor hpBar;
    private final UIActor mpBar;

    private int hpWidth;
    private int mpWidth;

    public UIIngame () {
        super(0,0);
        SetAsHolder();
        BlocheazaClick = false;
        stats = new UIActor(0,0);
        stats.setImage("gui/statDisplay.png");
        stats.Resize(2);
        stats.setPozitie(Lume.Instanta.getWidth()-stats.getImage().getWidth(),0);

        hpBar = new UIActor(0,0);
        hpBar.setImage("gui/hpBar.png");
        hpBar.Resize(2);
        hpBar.setPozitie(Lume.Instanta.getWidth()-stats.getImage().getWidth()+12,4);
        hpWidth = hpBar.getImage().getWidth();

        mpBar = new UIActor(0,0);
        mpBar.setImage("gui/mpBar.png");
        mpBar.Resize(2);
        mpBar.setPozitie(Lume.Instanta.getWidth()-stats.getImage().getWidth()+40,38);
        mpWidth = mpBar.getImage().getWidth();

    }

    @Override
    public void Update() {
        Player player = Lume.Instanta.player;
        if (player != null) {
            float hpFactor = (float) player.currentHp / player.maxHp;
            float mpFactor = (float) player.currentMana / player.maxMp;
            int wh = (int) (hpWidth * hpFactor);
            int wm = (int) (mpWidth * mpFactor);
            if (wh <= 0) wh = 1;
            if (wm <= 0) wm = 1;
            hpBar.Resize(wh, hpBar.getImage().getHeight());
            mpBar.Resize(wm, mpBar.getImage().getHeight());
        }
    }

    @Override
    public List<Actor> GetObiecte() {
        return new ArrayList<Actor>(){{
            add(stats);
            add(hpBar);
            add(mpBar);
        }};
    }
}
