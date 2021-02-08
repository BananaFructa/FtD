import java.util.ArrayList;
import java.util.List;

public class Item_nr extends UIActor {
    public Item tex;
    private int nrOb;
   public Item_nr(float x,float y,int nr){
       super(x,y);
       setImage("slot.png");
       getImage().scale(90,90);
       this.nrOb=nr;
   }
   public void addItem(Item item){
       tex=item;
       tex.setLocation(this.getX(),this.getY());
       tex.getImage().scale(90,90);
   }
   public Item getTex(){
       return tex;
   }
}
