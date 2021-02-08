import greenfoot.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory extends UIActor implements IContainer {
    private Map<String , Item_nr> slots=new HashMap<>();
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
                if (slots.get(key).getTex() != null) {
                    slots.get(key).getTex().getImage().setTransparency(v);
                }
                slots.get(key).getImage().setTransparency(v);
            }
        }
    }

//    public void addItem(Item item) {
//        boolean canAdd=true;
//        for(int i=0;i<4 && canAdd;i++){
//            for(int j=0;j<6 && canAdd;j++){
//                key=String.format("#%s#%s",i,j);
//                if(slots.get(key).getTex()==null) {
//                    slots.get(key).addItem(item);
//                    slots.get(key).getTex().getImage().setTransparency(0);
//                    canAdd=false;
//                }
//            }
//        }
//    }

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

    @Override
    public List<Actor> GetObiecte() {
        return new ArrayList<Actor>(){{
            String key;
            for(int i=0;i<4;i++){
                for(int j=0;j<6;j++){
                   key=String.format("#%s#%s",i,j);
                   add(slots.get(key));
                }
            }
        }};
    }
}
