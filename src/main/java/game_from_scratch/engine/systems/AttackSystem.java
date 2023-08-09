//package game_from_scratch.engine.systems;
//
//import game_from_scratch.engine.components.Attacking;
//import game_from_scratch.engine.components.Component;
//import game_from_scratch.engine.components.Moving;
//import game_from_scratch.engine.components.Position;
//
//import java.util.Optional;
//
//public class AttackSystem implements System<Attacking>, MovingSystem{
//    private Position position;
//
//    @Override
//    public void actOnOneComponent(Attacking attacking) {
//        Optional<Component> optionalMoving = attacking.getEntity().getComponentOfClass(Moving.class);
//        if (optionalMoving.isPresent()) {
//            Moving moving = (Moving) optionalMoving.get();
//            if (moving.intendsToMove()) {
//                Optional<Component> optionalPosition = attacking.getEntity().getComponentOfClass(Position.class);
//                if (optionalPosition.isPresent()) {
//                    this.position = (Position) optionalPosition.get();
//                    if (!this.collisionCheckPassed(moving)) {
//
//                    }
//                }
//            }
//            this.resetIntendedMove(moving);
//        }
//    }
//
//    //todo: onCollision?
//}
