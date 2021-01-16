public class Vector2f {

    public float x;
    public float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void Update() {

    }

    public Vector2f Aduna(Vector2f v) {
        return new Vector2f(x + v.x,y + v.y);
    }

    public Vector2f Scade(Vector2f v) {
        return new Vector2f(x - v.x,y - v.y);
    }

    public Vector2f MultiplicaScalar(float f) {
        return new Vector2f(x * f,y * f);
    }

}
