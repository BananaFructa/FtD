import java.util.ArrayList;

public class Inventory{
    private ArrayList<Item> items=new ArrayList<>();
    private static final int maxNrItems = 40;
    public Inventory(){

    }
    public void addItem(Item item){
        if(items.size()<maxNrItems){
            items.add(item);
        }
    }
}
