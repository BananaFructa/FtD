import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

import java.util.ArrayList;
import java.util.List;

public class UIIngame extends  UIActor implements IContainer {

    private final UIActor stats;
    private final UIActor hpBar;
    private final UIActor mpBar;

    private final UIActor heldSlot;
    private final UIActor itemDisplay;
    private final UIActor itemInfo;

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

        heldSlot = new UIActor(0,0);
        heldSlot.setImage("gui/heldSlot.png");
        heldSlot.Resize(3);

        heldSlot.setPozitie(Lume.Instanta.getWidth()-heldSlot.getImage().getWidth(),Lume.Instanta.getHeight()-heldSlot.getImage().getHeight());

        itemDisplay = new UIActor(0, 0);

        itemInfo = new UIActor(0,0);
        itemInfo.setImage("null.png");

        stats.BlocheazaClick = false;
        hpBar.BlocheazaClick = false;
        mpBar.BlocheazaClick = false;
        heldSlot.BlocheazaClick = false;
        itemDisplay.BlocheazaClick = false;
        itemInfo.BlocheazaClick = false;

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

            if (player.Inventar[0] != null) {
                itemInfo.setImage(new GreenfootImage("Damage: " + player.Inventar[0].Damage + "\n   Knockback: " + player.Inventar[0].KnockBack, 15, Color.BLACK, new Color(0, 0, 0, 0)));
                itemInfo.setPozitie(Lume.Instanta.getWidth() - itemInfo.getImage().getWidth() - 58, Lume.Instanta.getHeight() - itemInfo.getImage().getHeight() + 5);
                itemDisplay.setImage(player.Inventar[0].tex);
                itemDisplay.Resize(2);
                itemDisplay.setPozitie(Lume.Instanta.getWidth()-itemDisplay.getImage().getWidth()+8,Lume.Instanta.getHeight()-itemDisplay.getImage().getHeight()+8);
            } else {
                itemInfo.setImage("null.png");
                itemDisplay.setImage("null.png");
            }
        }
    }

    @Override
    public List<Actor> GetObiecte() {
        return new ArrayList<Actor>(){{
            add(stats);
            add(hpBar);
            add(mpBar);
            add(heldSlot);
            add(itemDisplay);
            add(itemInfo);
        }};
    }
}
