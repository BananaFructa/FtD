import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory extends BaseActor implements IContainer{
    private Map<String , Item_nr> slots=new HashMap<>();
    private String key ="";
    private int nr=0;
    private boolean open=false;

    public Inventory() {
      super(0,0);
      this.setImage("background.png");
      this.getImage().scale(600,400);
      for(int i=0;i<6;i++){
          for(int j=0;j<4;j++){
              key=String.format("#%s#%s",i,j);
              slots.put(key,new Item_nr(j*100,i*100,0));
          }
      }
      hide(open);
    }
    public void hide(boolean a){
        int v;
        if(a) {
            v=1;
        }else
            v=0;
            this.getImage().setTransparency(v);
            for(int i=0;i<6;i++){
                for(int j=0;j<4;j++){
                    key=String.format("#%s#%s",i,j);
                    if(slots.get(key).getTex()!=null)
                    slots.get(key).getTex().getImage().setTransparency(v);
                    slots.get(key).getImage().setTransparency(v);
                }
            }
    }

    public void addItem(Item item) {
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){
                key=String.format("#%s#%s",i,j);
                slots.get(key).addItem(item);
                break;
            }
        }
    }
    public void setOpen(boolean open){
        this.open=open;
    }
    public boolean getOpen(){
        return this.open;
    }
    public Map<String , Item_nr> getSlots(){
        return this.slots;
    }

    @Override
    public List<BaseActor> GetObiecte() {
        return new ArrayList<>(){{
            String key;
            for(int i=0;i<6;i++){
                for(int j=0;j<4;j++){
                   key=String.format("#%s#%s",i,j);
                   add(slots.get(key));
                }
            }
        }};
    }
}
