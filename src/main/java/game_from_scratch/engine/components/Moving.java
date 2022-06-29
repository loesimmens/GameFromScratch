package game_from_scratch.engine.components;

import game_from_scratch.engine.enums.Direction;

public class Moving extends Component {
    private boolean intendsToMove;
    private Direction intendedDirection;
    private int intendedXMove;
    private int intendedYMove;
    private int intendedXPosition;
    private int intendedYPosition;

    public int getIntendedXPosition() {
        return intendedXPosition;
    }

    public void setIntendedMoveX(int positionX, int intendedMoveX) {
        this.intendedXPosition = positionX + intendedMoveX;
        this.intendedXMove = intendedMoveX;
    }

    public int getIntendedYPosition() {
        return intendedYPosition;
    }

    public void setIntendedMoveY(int positionY, int intendedMoveY) {
        this.intendedYPosition = positionY + intendedMoveY;
        this.intendedYMove = intendedMoveY;
    }

    public Direction getIntendedDirection() {
        return intendedDirection;
    }

    public void setIntendedDirection(Direction intendedDirection) {
        this.intendedDirection = intendedDirection;
    }

    public boolean intendsToMove() {
        return intendsToMove;
    }

    public void setIntendsToMove(boolean intendsToMove) {
        this.intendsToMove = intendsToMove;
    }

    public void resetIntendedMove() {
        intendedXMove = 0;
        intendedYMove = 0;
        intendsToMove = false;
    }

    public int getIntendedXMove() {
        return intendedXMove;
    }

    public int getIntendedYMove() {
        return intendedYMove;
    }

    @Override
    public String toString() {
        return "Moving{" +
                "entity=" + entity +
                ", intendsToMove=" + intendsToMove +
                ", intendedDirection=" + intendedDirection +
                ", intendedMoveX=" + intendedXMove +
                ", intendedMoveY=" + intendedYMove +
                ", intendedPositionX=" + intendedXPosition +
                ", intendedPositionY=" + intendedYPosition +
                '}';
    }
}
