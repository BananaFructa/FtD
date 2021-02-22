import java.util.ArrayList;
import java.util.List;

public class InamicBall extends Inamic {

    Animatie normalAnimatie;

    public InamicBall(int x,int y) {
        super(x,y,20,0.6f,100,20);
        normalAnimatie = new Animatie(DateAnimatii.BALL_ANIMATION,this);
        normalAnimatie.Start();
    }

    @Override
    public List<Tuple<Item, Float>> GetDrops() {
        return new ArrayList<Tuple<Item, Float>>(){{
            add(new Tuple<>(Items.CellWand,0.1f));
        }};
    }
}
