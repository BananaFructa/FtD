import java.util.ArrayList;
import java.util.List;

public class InamicSludge extends Inamic {

    Animatie normalAnimatie;

    public InamicSludge(int x,int y) {
        super(x,y,40,1.1f,100,20);
        normalAnimatie = new Animatie(DateAnimatii.SLUDGE_ANIMATION,this);
        normalAnimatie.Start();
    }

    @Override
    public List<Tuple<Item, Float>> GetDrops() {
        return new ArrayList<Tuple<Item, Float>>() {{
           add(new Tuple<>(Items.ToxiWand,20f));
        }};
    }
}
