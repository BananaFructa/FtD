public class Interfete {

    public static Inventory Inventar;

    public static void Init() {
        Inventar = new Inventory();
        Lume.Instanta.managerObiecte.InregistreazaInterfata(Inventar);
    }
}
