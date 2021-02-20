public class Interfete {

    public static UIInventory Inventar;
    public static UIIngame Ingame;

    public static void Init() {
        Inventar = new UIInventory();
        Lume.Instanta.managerObiecte.InregistreazaInterfata(Inventar);
        Inventar.Inchide();

        Ingame = new UIIngame();
        Lume.Instanta.managerObiecte.InregistreazaInterfata(Ingame);
        Ingame.Deschide();
    }
}
