import greenfoot.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory extends UIActor implements IContainer {
    private Map<String , Item_nr> slots=new HashMap<>();
    private Map<String ,Item_nr> items=new HashMap<>();
    private String key ="";
    private int nr=0;

    public Inventory() {
      super(300,200);
      this.setImage("background.png");
      this.getImage().scale(600,400);
      for(int i=0;i<4;i++){
          for(int j=0;j<6;j++){
              key=String.format("#%s#%s",i,j);
              slots.put(key,new Item_nr(j*100+47,i*100+47,0));
          }
      }
      hide(Deschis);
    }

    public void hide(boolean a) {
        int v;
        if (a) {
            v = 255;
        } else
            v = 0;
        this.getImage().setTransparency(v);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                key = String.format("#%s#%s", i, j);
                if (items.get(key)!= null) {
                    items.get(key).getImage().setTransparency(v);
                }
                slots.get(key).getImage().setTransparency(v);
            }
        }
    }

    public void addItem(Item item) {
        boolean canAdd=true;
        if(item !=null) {
            for (int i = 0; i < 4 && canAdd; i++) {
                for (int j = 0; j < 6 && canAdd; j++) {
                    key = String.format("#%s#%s", i, j);
                    if (!items.containsKey(key)) {
                        items.put(key ,new Item_nr(slots.get(key).getX(),slots.get(key).getY(),0));
                        items.get(key).addItem(item);
                        items.get(key).setImage("fig.png");
                        items.get(key).getImage().setTransparency(0);
                        canAdd = false;
                    }
                }
            }
        }
    }

    @Override
    public void Deschide() {
        hide(true);
    }

    @Override
    public void Inchide() {
        hide(false);
    }

    public Map<String , Item_nr> getSlots(){
        return this.slots;
    }
    public Map<String , Item_nr> getItems(){
        return this.items;
    }

    @Override
    public List<Actor> GetObiecte() {
        return new ArrayList<Actor>(){{
            String key;
            for(int i=0;i<4;i++){
                for(int j=0;j<6;j++){
                   key=String.format("#%s#%s",i,j);
                   add(slots.get(key));
                   if(items.containsKey(key))
                   add(items.get(key));
                }
            }
        }};
    }
}
