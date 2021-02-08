import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.HashMap;

public class InputKeyboard {

    public enum Key {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
    }

    InputKeyboard() {
        StergeStatus();
    }

    HashMap<Key, Boolean> UltimStatus = new HashMap<Key, Boolean>();
    HashMap<Key, Boolean> CurentStatus = new HashMap<Key, Boolean>();

    void UpdateazaCurentStatus() {
        for (Key k : Key.values()) {
            CurentStatus.put(k, Greenfoot.isKeyDown(k.name()));
        }
    }

    void UpdateazaUltimStatus() {
        for (Key k : Key.values()) {
            UltimStatus.put(k, CurentStatus.get(k));
        }
    }

    void StergeStatus() {
        for (Key k : Key.values()) {
            CurentStatus.put(k, false);
            UltimStatus.put(k, false);
        }
    }

    public boolean ApasatInstantaneu(Key k) {
        return CurentStatus.get(k) && UltimStatus.get(k);
    }

    public boolean EsteApasat(Key k) {
        return CurentStatus.get(k);
    }

}
