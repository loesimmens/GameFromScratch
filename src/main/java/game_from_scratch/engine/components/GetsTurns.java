package game_from_scratch.engine.components;

import game_from_scratch.game.logging.GameLogger;

import java.util.logging.Logger;

public class GetsTurns extends Component {
    private static final Logger LOGGER = GameLogger.getLogger();
    private final int nTurns;
    private int turnsLeft;

    public GetsTurns(int nTurns) {
        this.nTurns = nTurns;
        this.turnsLeft = nTurns;
    }

    public void decreaseTurnsLeft() {
        this.turnsLeft--;
    }

    public void restoreTurnsLeft() {
        this.turnsLeft = nTurns;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    @Override
    public String toString() {
        return "GetsTurns{" +
                "nTurns=" + nTurns +
                ", turnsLeft=" + turnsLeft +
                '}';
    }
}
