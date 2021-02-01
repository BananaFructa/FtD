import java.util.*;

public class ManagerObiecte {

    private static boolean Updateaza = true;
    private final int UpdateDelay = 50;
    public static List<BaseActor> Actori = new ArrayList<BaseActor>();

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
        synchronized (this) {
            for (BaseActor actor : Actori) {
                actor.Update();
            }
        }
    }

    public void AdaugaActor(BaseActor actor) {
        synchronized (this) {
            Lume.Instanta.addObject(actor, (int) actor.Pozitie.x, (int) actor.Pozitie.y);
            Actori.add(actor);
            if (actor instanceof IContainer) {
                List<BaseActor> subActori = ((IContainer) actor).GetObiecte();
                for (BaseActor a : subActori) {
                    AdaugaActor(a);
                }
            }
            actor.Init();
        }
    }

    public void DisterugeActor(BaseActor actor) {
        Lume.Instanta.removeObject(actor);
    }



}
