public class Tuple <T,U> {
    public T Prim;
    public U Secund;

    public Tuple(T p,U s) {
        Prim = p;
        Secund = s;
    }

    public T Prim() {
        return Prim;
    }

    public U Secund() {
        return Secund;
    }
}
