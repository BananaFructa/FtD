import greenfoot.Greenfoot;

public class UISlot extends UIActor {

    public int ID;
    public Item item;
    public UIInventory inv;

    public UISlot(int x,int y,int ID,UIInventory inv) {
        super(x,y);
        this.ID = ID;
        this.inv = inv;
    }

    public Item SetItem(Item i) {
        Item old = item;
        item = i;
        if (i == null) this.setImage("null.png");
        else this.setImage(item.tex);
        this.getImage().scale(12*2,12*2);
        return old;
    }

    @Override
    public void Update() {
        if (Lume.Instanta.inputMouse.Apasat() && Lume.Instanta.inputMouse.Obiect == this) {
            this.SetItem(inv.CursorSlot.SetItem(item));
            Lume.Instanta.player.SetItem(ID,item);
        }
    }
}
