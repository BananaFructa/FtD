import java.util.*;

public class ManagerObiecte {

    private static boolean Updateaza = true;
    private final int UpdateDelay = 50;
    public static List<BaseActor> Actori = new ArrayList<BaseActor>();
    private static List<BaseActor> ActoriDeAdaugat = new ArrayList<>();

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
            Actori.addAll(ActoriDeAdaugat);
            for (BaseActor a : ActoriDeAdaugat) {
                Lume.Instanta.addObject(a, (int) a.Pozitie.x, (int) a.Pozitie.y);
                a.Init();
            }
            ActoriDeAdaugat.clear();
        }
    }

    public void AdaugaActor(BaseActor actor) {
        synchronized (this) {
            ActoriDeAdaugat.add(actor);
            if (actor instanceof IContainer) {
                List<BaseActor> subActori = ((IContainer) actor).GetObiecte();
                for (BaseActor a : subActori) {
                    AdaugaActor(a);
                }
            }
        }
    }

    public void DisterugeActor(BaseActor actor) {
        Lume.Instanta.removeObject(actor);
    }



}
