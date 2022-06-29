package game_from_scratch.engine.enums;

public enum InputKey {
    W('w'),
    A('a'),
    S('s'),
    D('d'),
    X('x'),
    E('e'),
    NONE(' ');

    private char key;

    InputKey(char key) {
    }

    public static InputKey fromChar(char key) {
        switch (key) {
            case 'w': return W;
            case 'a': return A;
            case 's': return S;
            case 'd': return D;
            case 'x': return X;
            case 'e': return E;
            default: return NONE;
        }
    }
}
