import java.util.*;

public class ManagerObiecte {

    private static boolean Updateaza = true;
    private final int UpdateDelay = 50;

    public static List<UIActor> Interfete = new ArrayList<>();

    public static List<BaseActor> Actori = new ArrayList<>();
    private static List<BaseActor> ActoriDeAdaugat = new ArrayList<>();
    private static List<BaseActor> ActoriDeDistrus = new ArrayList<>();

    public void Init() {
        Thread UpdateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (; Updateaza; Thread.sleep(UpdateDelay)) {
                        Lume.Instanta.inputKeyboard.UpdateazaCurentStatus();
                        Update();
                        Lume.Instanta.inputMouse.SetApasat(false);
                        Lume.Instanta.inputKeyboard.UpdateazaUltimStatus();
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
        try {
            for (BaseActor actor : Actori) {
                actor.Update();
            }
            for (UIActor actor : Interfete) {
                actor.Update();
            }
        } catch (ConcurrentModificationException exception) {
            // Nu conteaza
        }
        Actori.addAll(ActoriDeAdaugat);
        Actori.removeAll(ActoriDeDistrus);
        for (BaseActor actor : ActoriDeAdaugat) {
            Lume.Instanta.addObject(actor, (int) actor.Pozitie.x, (int) actor.Pozitie.y);
            actor.Init();
        }
        for (BaseActor actor : ActoriDeDistrus) {
            Lume.Instanta.removeObject(actor);
        }
        ActoriDeAdaugat.clear();
        ActoriDeDistrus.clear();
    }

    public void AdaugaActor(BaseActor actor) {
        ActoriDeAdaugat.add(actor);
        if (actor instanceof IContainer) {
            List<BaseActor> subActori = (List<BaseActor>)(List<?>)((IContainer) actor).GetObiecte();
            for (BaseActor a : subActori) {
                AdaugaActor(a);
            }
        }
    }

    public void DisterugeActor(BaseActor actor) {
        ActoriDeDistrus.add(actor);
        if (actor instanceof IContainer) {
            List<BaseActor> subActori = (List<BaseActor>)(List<?>)((IContainer) actor).GetObiecte();
            for (BaseActor a : subActori) {
                DisterugeActor(a);
            }
        }
    }

    public void InregistreazaInterfata(UIActor actor) {
        Interfete.add(actor);
        Lume.Instanta.addObject(actor, (int) actor.Pozitie.x, (int) actor.Pozitie.y);
        if (actor instanceof IContainer) {
            List<UIActor> subInterfete = (List<UIActor>)(List<?>)((IContainer) actor).GetObiecte();
            for (UIActor a : subInterfete) {
                InregistreazaInterfata(a);
            }
        }
        actor.Init();
    }
}
