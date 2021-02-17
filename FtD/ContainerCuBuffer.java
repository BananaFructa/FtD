import java.util.ArrayList;
import java.util.List;

public class ContainerCuBuffer <T> {

    List<T> Normal = new ArrayList<>();
    List<T> Adauga = new ArrayList<>();
    List<T> Elimina = new ArrayList<>();

    public void Elimina(T Elem) {
        Elimina.add(Elem);
    }

    public void Adauga(T Elem) {
        Adauga.add(Elem);
    }

    public void CompleteazaTranzactiile() {
        if (!Adauga.isEmpty())Normal.addAll(Adauga);
        if (!Elimina.isEmpty())Normal.removeAll(Elimina);
    }

    public void StergeBufferele() {
        Adauga.clear();
        Elimina.clear();
    }

}
