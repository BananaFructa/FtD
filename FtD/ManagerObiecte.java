import java.util.*;

public class ManagerObiecte {

    private static boolean Updateaza = true;
    private final int UpdateDelay = 50;
    static List<BaseActor> Actori = new ArrayList<BaseActor>();

    public void Init() {
        Thread UpdateThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    for (; Updateaza; Thread.sleep(UpdateDelay)) {
                        Update();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        UpdateThread.start();
    }

    public void OpresteUpdatarea() {
        Updateaza = false;
    }

    public void Update() {
        for (BaseActor actor : Actori) {
            actor.Update();
        }
    }

    public void AdaugaActor(BaseActor actor) {
        Lume.Instanta.addObject(actor,(int)actor.Pozitie.x,(int)actor.Pozitie.y);
        Actori.add(actor);
        actor.Init();
    }

    public void DisterugeActor(BaseActor actor) {
        Lume.Instanta.removeObject(actor);
    }



}
