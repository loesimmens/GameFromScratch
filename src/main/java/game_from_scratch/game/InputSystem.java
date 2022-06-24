package game_from_scratch.game;

import game_from_scratch.game.logging.GameLogger;
import org.springframework.stereotype.Service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

@Service
public class InputSystem implements KeyListener {
    private static final Logger LOGGER = GameLogger.getLogger();

    private final boolean[] keys;
    private final boolean[] justPressed;
    private final boolean[] cantPress;

    public InputSystem() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(!(keyEvent.getKeyCode() < 0 || keyEvent.getKeyCode() > keys.length)) {
            keys[keyEvent.getKeyCode()] = true;
            LOGGER.info("Key pressed: " + keyEvent.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)) {
            keys[e.getKeyCode()] = false;
            LOGGER.info("Key released: " + e.getKeyCode());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException();
    }

    public void tick() {
        for(var i = 0; i < keys.length; i++) {
            if(pressedThenReleased(i)) {
                cantPress[i] = false;
//                notify(-1);
                LOGGER.info("Notify all listeners that key " + i + " has just been released");
            }
            else if(justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
                LOGGER.info("Can't press key " + i + " again, because it has just been pressed");
            }
            if(!cantPress[i] && keys[i]) {
                justPressed[i] = true;
//                notify(i);
                LOGGER.info("Notify all listeners that key " + i + " has just been pressed");
            }
        }

        boolean up = keys[KeyEvent.VK_W];
        if(up) {
            LOGGER.info("UP has been pressed");
        }
//        down = keys[KeyEvent.VK_S];
//        left = keys[KeyEvent.VK_A];
//        right = keys[KeyEvent.VK_D];
//
//        interact = keys[KeyEvent.VK_E];
//        attack = keys[KeyEvent.VK_X];
//
//        one = keys[KeyEvent.VK_1];
//        two = keys[KeyEvent.VK_2];
    }

    private boolean pressedThenReleased(int keyCode) {
        return cantPress[keyCode] && !keys[keyCode];
    }

    private boolean hasJustBeenPressed(int keyCode) {
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }
}
