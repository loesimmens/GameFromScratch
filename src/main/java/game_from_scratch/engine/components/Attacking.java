package game_from_scratch.engine.components;

public class Attacking extends Component {
    private int attackModifier;

    public Attacking(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    @Override
    public String toString() {
        return "Attacking{" +
                "attackModifier=" + attackModifier +
                '}';
    }
}
