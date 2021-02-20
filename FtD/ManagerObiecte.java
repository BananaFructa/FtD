import java.util.*;

public class ManagerObiecte {

    private static boolean Updateaza = true;
    private final int UpdateDelay = 50; // 20 de ori pe secunda

    public static List<UIActor> Interfete = new ArrayList<>();

    public static ContainerCuBuffer<BaseActor> Actori = new ContainerCuBuffer<>();
    public static ContainerCuBuffer<Animatie> Animatii = new ContainerCuBuffer<>();

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
        synchronized (this) {
            try {
                for (BaseActor actor : Actori.Normal) {
                    actor.Update();
                }
                for (UIActor actor : Interfete) {
                    actor.Update();
                }

                Actori.CompleteazaTranzactiile();
                for (BaseActor actor : Actori.Adauga) {
                    Lume.Instanta.addObject(actor, (int) actor.Pozitie.x, (int) actor.Pozitie.y);
                    actor.Init();
                }
                for (BaseActor actor : Actori.Elimina) {
                    Lume.Instanta.removeObject(actor);
                }
                Actori.StergeBufferele();

                Animatii.CompleteazaTranzactiile();
                Animatii.StergeBufferele();

                for (Animatie animatie : Animatii.Normal) {
                    animatie.Next();
                }
            } catch (ConcurrentModificationException exception) {

            }
        }
    }

    public void AdaugaActor(BaseActor actor) {
        Actori.Adauga(actor);
        if (actor instanceof IContainer) {
           // System.out.println("A");
            List<BaseActor> subActori = (List<BaseActor>) (List<?>) ((IContainer) actor).GetObiecte();
            for (BaseActor a : subActori) {
                AdaugaActor(a);
            }
        }

    }

    public void DisterugeActor(BaseActor actor) {
        Actori.Elimina(actor);
        if (actor instanceof IContainer) {
            List<BaseActor> subActori = (List<BaseActor>) (List<?>) ((IContainer) actor).GetObiecte();
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

    public void DistrugeInterfata(UIActor actor) {
        if (Interfete.contains(actor)) {
            Interfete.remove(actor);
            Lume.Instanta.removeObject(actor);
        }
    }

    public void InregistreazaAnimatie(Animatie animatie) {
        Animatii.Adauga(animatie);
    }

    public void DistrugeAnimatie(Animatie animatie) {
        Animatii.Elimina(animatie);
    }
}
