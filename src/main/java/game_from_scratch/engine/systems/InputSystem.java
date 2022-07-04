package game_from_scratch.engine.systems;

import game_from_scratch.engine.components.Moving;
import game_from_scratch.engine.components.TakesInput;
import game_from_scratch.engine.entities.Entity;
import game_from_scratch.engine.enums.Direction;
import game_from_scratch.engine.enums.InputKey;
import org.springframework.stereotype.Service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

@Service
public class InputSystem implements KeyListener, System<TakesInput>, InputForMovementSystem {
    private InputKey inputKey;
    private boolean inputHandled;

    public InputSystem() {
        this.inputKey = InputKey.NONE;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        this.inputKey = InputKey.fromChar(keyEvent.getKeyChar());
        this.inputHandled = false;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(this.inputHandled) {
            this.inputKey = InputKey.NONE;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //not implemented
    }

    @Override
    public void actOnOneComponent(TakesInput takesInput) {
        Entity entity = takesInput.getEntity();
        if(!this.inputHandled) {
            setDirectionForMoving(entity);
            this.inputHandled = true;
        } else {
            //menus?
        }
    }

    @Override
    public void setDirectionForMoving(Entity entity) {
        Moving moving;
        Optional<Moving> optionalMoving = entity.getComponents().stream()
                .filter(Moving.class::isInstance)
                .map(Moving.class::cast)
                .findFirst();
        if(optionalMoving.isPresent()) {
            moving = optionalMoving.get();
            switch (this.inputKey) {
                case W: moving.setIntendsToMove(true); moving.setIntendedDirection(Direction.UP); break;
                case A: moving.setIntendsToMove(true); moving.setIntendedDirection(Direction.LEFT); break;
                case S: moving.setIntendsToMove(true); moving.setIntendedDirection(Direction.DOWN); break;
                case D: moving.setIntendsToMove(true); moving.setIntendedDirection(Direction.RIGHT); break;
                default: moving.setIntendsToMove(false); break;
            }
        }
    }
}
