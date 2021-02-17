public class Interfete {

    public static UIInventory Inventar;

    public static void Init() {
        Inventar = new UIInventory();
        Lume.Instanta.managerObiecte.InregistreazaInterfata(Inventar);
        Inventar.Inchide();
    }
}
