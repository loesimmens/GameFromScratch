package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Component;
import game_from_scratch.engine.components.GetsTurns;
import game_from_scratch.engine.components.TakesInput;
import game_from_scratch.engine.enums.WhoseTurn;
import game_from_scratch.game.logging.GameLogger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TurnSystem implements System <GetsTurns> {
    private static final Logger LOGGER = GameLogger.getLogger();

    private WhoseTurn whoseTurn;
    private final List<GetsTurns> getsTurnsList;

    public TurnSystem() {
        this.whoseTurn = WhoseTurn.AI;
        this.getsTurnsList = new ArrayList<>();
    }

    @Override
    public void actOnOneComponent(GetsTurns getsTurns) {
        if(!this.getsTurnsList.contains(getsTurns)) {
            this.getsTurnsList.add(getsTurns);
        }
        if(this.isMyTurn(getsTurns)) {
            this.handleTurns(getsTurns);
        }
    }

    private boolean isMyTurn(GetsTurns getsTurns) {
        if(this.isPlayer(getsTurns) && this.whoseTurn == WhoseTurn.PLAYER) {
            return true;
        } else {
            return !this.isPlayer(getsTurns) && this.whoseTurn == WhoseTurn.AI;
        }
    }

    private boolean isPlayer(GetsTurns getsTurns) {
        Optional<Component> optionalPlayer = getsTurns.getEntity().getComponentOfClass(TakesInput.class);
        return optionalPlayer.isPresent();
    }

    private void handleTurns(GetsTurns getsTurns) {
        if(getsTurns.getTurnsLeft() == 0) {
            this.changeTurn();
        }
    }

    private void changeTurn() {
        if(this.whoseTurn == WhoseTurn.AI) {
            this.whoseTurn = WhoseTurn.PLAYER;
        } else {
            this.whoseTurn = WhoseTurn.AI;
        }
        this.getsTurnsList.stream()
                .filter(this::isMyTurn)
                .forEach(GetsTurns::restoreTurnsLeft);
    }

    public WhoseTurn getWhoseTurn() {
        return whoseTurn;
    }
}
