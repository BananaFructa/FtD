public class Animatie {

    DateAnimatii Info;
    BaseActor parent;
    boolean Ruleaza = false;
    int IDX = 0;
    int cadru = 0;

    public Animatie(DateAnimatii dateAnimatii,BaseActor parent) {
        this.Info = dateAnimatii;
        this.parent = parent;
        Lume.Instanta.managerObiecte.InregistreazaAnimatie(this);
    }

    public void Next() {
        if (Ruleaza) {
            cadru++;
            if (cadru == Info.fps) {
                cadru = 0;
                IDX++;
                if (Info.secventa.length <= IDX) IDX = 0;
                parent.setImage(Info.secventa[IDX]);
            }
        }
    }

    public void Start() {
        if (!Ruleaza) {
            Ruleaza = true;
            parent.setImage(Info.secventa[0]);
            cadru = 0;
        }
    }

    public void Stop() {
        if (Ruleaza) {
            Ruleaza = false;
            IDX = 0;
            //parent.FallBackTexToDef();
        }
    }

}
